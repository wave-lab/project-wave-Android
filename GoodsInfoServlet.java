import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class GoodsInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String lowerCode = request.getParameter("LAST_CODE");
		if (lowerCode == null)
			lowerCode = "00000";
		GoodsInfo goodsInfo = readDB(lowerCode);
		request.setAttribute("Good_INFO", goodsInfo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("MyHomepage.html?BODY_PATH=GoodsInfoView.jsp");
		dispatcher.forward(request, response);
	}
	private GoodsInfo readDB(String lowerCode) throws ServletException {
		GoodsInfo goodsInfo = new GoodsInfo();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_programming", "root", "1234");
			if (conn == null)
				throw new Exception("데이터베이스에연결할수없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("select *" + "from goodsinfo where code > '" + lowerCode + "' order by code asc; ");
			for (int cnt = 0; cnt < 5; cnt++) {
				if (!rs.next())
					break;
				goodsInfo.setCode(cnt, rs.getString("code"));
				goodsInfo.setName(cnt, rs.getString("name"));
				goodsInfo.setPrice(cnt, rs.getInt("price"));
				goodsInfo.setStock(cnt, rs.getString("stock"));
			}
			if (!rs.next())
				goodsInfo.setLastPage(true);
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				stmt.close();
			} catch (Exception ignored) {
			}
			try {
				conn.close();
			} catch (Exception ignored) {
			}
		}
		return goodsInfo;
	}
}
