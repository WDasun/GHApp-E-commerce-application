package common;

import dto.CurrentEmployee;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import security.Log.CreateLog;

/**
 *
 * @author Dasun Wimukthi
 */
public class SessionClose implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
        HttpSession session = se.getSession();
        CurrentEmployee currentEmployee = (CurrentEmployee) session.getAttribute("CurrentEmployee");
        
        if (currentEmployee != null) {
            // Log Employee Activity
            new CreateLog("info", "Employee session closed.", currentEmployee.getEmployeeId(), SessionClose.class.getName()).employeeActivey();
        }
        
    }

}
