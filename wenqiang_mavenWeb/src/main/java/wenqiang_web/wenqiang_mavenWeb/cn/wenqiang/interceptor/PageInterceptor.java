package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.RowBounds;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })  
public class PageInterceptor implements Interceptor {  
  
	private  static final String databaseType="mysql";
	
	
    @Override  
    public Object intercept(Invocation invocation) throws Throwable {  
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();  
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);  
        BoundSql boundSql = statementHandler.getBoundSql();  
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");  
        String id = mappedStatement.getId();  
        System.out.println("sql语句的id : " + id);  
        if (id.matches(".+ByPage$")) {
        Object param = boundSql.getParameterObject();  
        if (null != param && Map.class.isAssignableFrom(param.getClass())) {  
            Map paramMap = (Map) param;  
            Object _pageSize = paramMap.get("pageSize");  
            Object _pageNo = paramMap.get("pageNo");  
            if (_pageNo != null && _pageSize != null) {  
                int pageSize = (Integer) _pageSize;  
                int pageNo = (Integer) _pageNo;  
                setTotalResult(boundSql, (Connection) invocation.getArgs()[0], metaStatementHandler, paramMap);  
                String sql = getPageSql(pageSize, pageNo, boundSql);  
                metaStatementHandler.setValue("delegate.boundSql.sql", sql);  
                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);  
                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);  
               }  
          }  
        }
        return invocation.proceed();  
    }  
  
        // 这个分页方法是借助于网上的  
    private String getPageSql(int pageSize, int pageNo, BoundSql boundSql) {  
    	if(this.databaseType.equalsIgnoreCase("MySql")){
    		StringBuffer sb = new StringBuffer();
    		sb.append(boundSql.getSql());
    		int page=pageSize * (pageNo-1);
    		sb.append(" limit "+page+","+pageSize);
    		return sb.toString();
		
		}else{
			StringBuffer sql = new StringBuffer(boundSql.getSql());  
	        sql.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(pageSize * pageNo);  
	        sql.insert(0, "select * from (").append(") where r >= ").append((pageSize - 1) * pageNo);  
	        return sql.toString();
		}
    }  
    private void setTotalResult(BoundSql boundSql, Connection conn, MetaObject metaObject, Map param) throws SQLException {  
        String countSql = "select count(*) from ( " + boundSql.getSql() + " ) total";  
        PreparedStatement prepareStatement = conn.prepareStatement(countSql);  
        ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");  
        parameterHandler.setParameters(prepareStatement); // 给sql语句设置参数  
        ResultSet resultSet = prepareStatement.executeQuery();  
        if (resultSet.next()) {  
            param.put("total", resultSet.getObject(1));  
        }  
        if (resultSet != null) {  
            resultSet.close();  
        }  
        if (prepareStatement != null) {  
            prepareStatement.close();  
        }  
    }

    @Override  
    public Object plugin(Object target) {  
        return Plugin.wrap(target, this);  
    }  
    @Override  
    public void setProperties(Properties properties) {  
    
    }  
  
   
}  