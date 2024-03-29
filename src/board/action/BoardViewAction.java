package board.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardViewAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));

		Cookie cookie = request.getCookies()[0];
		if (cookie != null) {
			if (cookie.getName().equals("view")) {
				BoardDAO.getInstance().boardHit(seq);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}

		BoardDTO boardDTO = BoardDAO.getInstance().getBoard(seq);

		request.setAttribute("boardDTO", boardDTO);
		request.setAttribute("pg", pg);
		request.setAttribute("display", "/board/boardView.jsp");

		return "/main/index.jsp";
	}

}
