package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.GenreDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Genre;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.ADD_FILM_PAGE_PATH;

public class AllFilmsPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(AllFilmsPageCommand.class);
    private String classSimpleName = AllFilmsPageCommand.class.getSimpleName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        /*try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + classSimpleName + " execute command");
            final GenreDAO genreDAO = factory.getGenreDAO();
            final List<Genre> genreList = genreDAO.findAll();
            request.setAttribute("genreList", genreList);
            logger.debug("Forward to add film page");
            request.getRequestDispatcher(ADD_FILM_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            logger.error("Couldn't execute " + classSimpleName + " command", e);
        }*/
    }
}
