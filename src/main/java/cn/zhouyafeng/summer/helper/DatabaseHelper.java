package cn.zhouyafeng.summer.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.summer.utils.CollectionUtil;
import cn.zhouyafeng.summer.utils.PropsUtil;

/**
 * 数据库操作助手类
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年7月1日 下午5:05:22
 * @version 1.0
 *
 */
public final class DatabaseHelper {
	private static final Logger LOG = LoggerFactory.getLogger(DatabaseHelper.class);

	private static final ThreadLocal<Connection> CONNECTION_HOLDER;

	private static final QueryRunner QUERY_RUNNER;

	private static final BasicDataSource DATA_SOURCE;

	static {

		CONNECTION_HOLDER = new ThreadLocal<Connection>();
		QUERY_RUNNER = new QueryRunner();

		Properties conf = PropsUtil.loadProps("config.properties");
		String driver = conf.getProperty("jdbc.driver");
		String url = conf.getProperty("jdbc.url");
		String username = conf.getProperty("jdbc.username");
		String password = conf.getProperty("jdbc.password");

		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(driver);
		DATA_SOURCE.setUrl(url);
		DATA_SOURCE.setUsername(username);
		DATA_SOURCE.setPassword(password);
	}

	/**
	 * 数据库连接
	 * 
	 * @date 2017年7月1日 下午5:09:23
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if (conn == null) {
			try {
				conn = DATA_SOURCE.getConnection();
			} catch (SQLException e) {
				LOG.error("get connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
		return conn;
	}

	/**
	 * 关闭数据连接
	 * 
	 * @date 2017年7月1日 下午5:21:12
	 * @param conn
	 */
	public static void closeConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				LOG.error("close connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}

	/**
	 * 查询实体列表
	 * 
	 * @date 2017年7月1日 下午5:26:41
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
		List<T> entityList;
		try {
			Connection conn = getConnection();
			entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOG.error("query entity list error", e);
			throw new RuntimeException(e);
		}
		return entityList;
	}

	/**
	 * 查询实体
	 * 
	 * @date 2017年7月1日 下午10:13:47
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
		T entity = null;
		try {
			Connection conn = getConnection();
			entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOG.error("query entity list failure", e);
			throw new RuntimeException(e);
		}
		return entity;
	}

	/**
	 * 执行查询语句
	 * 
	 * @date 2017年7月1日 下午10:32:03
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> excuteQuery(String sql, Object... params) {
		List<Map<String, Object>> result;
		try {
			Connection conn = getConnection();
			result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
		} catch (SQLException e) {
			LOG.error("execute query error", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 执行更新操作，包括insert, update, delete
	 * 
	 * @date 2017年7月1日 下午10:36:47
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql, Object... params) {
		int rows = 0;
		try {
			Connection conn = getConnection();
			rows = QUERY_RUNNER.update(conn, sql, params);
		} catch (Exception e) {
			LOG.error("execute update error", e);
			throw new RuntimeException(e);
		}
		return rows;
	}

	/**
	 * 插入实体
	 * 
	 * @date 2017年7月1日 下午10:52:08
	 * @param entityClass
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOG.error("can not insert entity: fieldMap is empty");
			return false;
		}

		String sql = "INSERT INTO " + getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		sql += columns + " VALUES " + values;

		Object[] params = fieldMap.values().toArray();
		return executeUpdate(sql, params) == 1;
	}

	/**
	 * 更新实体
	 * 
	 * @date 2017年7月1日 下午10:53:29
	 * @param entityClass
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOG.error("can not update entity: fieldMap is empty");
			return false;
		}

		String sql = "UPDATE " + getTableName(entityClass) + " SET";
		StringBuilder columns = new StringBuilder();
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append("=?, ");
		}
		sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id=?";

		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();

		return executeUpdate(sql, params) == 1;

	}

	/**
	 * 删除实体
	 * 
	 * @date 2017年7月1日 下午11:05:07
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
		String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id=?";
		return executeUpdate(sql, id) == 1;
	}

	private static String getTableName(Class<?> entityClass) {
		return entityClass.getSimpleName();
	}

	/**
	 * 执行sql文件
	 * 
	 * @date 2017年7月2日 上午11:58:06
	 * @param filePath
	 */
	public static void executeSqlFile(String filePath) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String sql;
		try {
			while ((sql = reader.readLine()) != null) {
				executeUpdate(sql);
			}
		} catch (Exception e) {
			LOG.error("execute sql file error", e);
			throw new RuntimeException(e);
		}
	}
}
