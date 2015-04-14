package socialstreet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Utils {
	
	/**
	 *  
	 * Function to create connection with database and to read the xml files
	 * used for mapping.
	 */
	public static SqlSession getSqlSession() {
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		try {
			
			String resource = "mybatis/mybatisConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); 
			sqlSession = sqlSessionFactory.openSession();
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return sqlSession;
	}

}
