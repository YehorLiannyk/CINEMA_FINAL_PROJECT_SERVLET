package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Session;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.SessionService;
import yehor.epam.services.impl.ErrorServiceImpl;
import yehor.epam.services.impl.SessionServiceImpl;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.constants.OtherConstants;

import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.JspPagePathConstants.SCHEDULE_PAGE_PATH;
import static yehor.epam.utilities.constants.OtherConstants.DEF_PAGING_SIZE;

/**
 * Command to set Schedule page
 */
public class ScheduleCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(ScheduleCommand.class);
    private static final String CLASS_NAME = ScheduleCommand.class.getName();
    private final SessionService sessionService;

    public ScheduleCommand() {
        sessionService = new SessionServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in {}", CLASS_NAME);
        try {
            final Map<String, String[]> parameterMap = request.getParameterMap();
            final Map<String, String> filterSortMap = sessionService.getFilterSortMapFromParams(parameterMap);
            List<Session> sessionList = getAppropriateSessionList(filterSortMap);
            request.setAttribute("sessionList", sessionList);
            request.getRequestDispatcher(SCHEDULE_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorServiceImpl.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Return usual SessionList if Schedule page wasn't sort or filter
     *
     * @param filterSortMap Map contains only sorting and filtering params
     * @return usual SessionList or already filtered and sorted
     */
    private List<Session> getAppropriateSessionList(Map<String, String> filterSortMap) throws ServiceException {
        if (!filterSortMap.isEmpty())
            return sessionService.getFilteredAndSortedSessionList(filterSortMap);
        logger.debug("Return not sorted session list");
        return sessionService.getAll();
    }
}
