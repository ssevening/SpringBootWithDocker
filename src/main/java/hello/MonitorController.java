package hello;

import hello.dao.pojo.MonitorInfo;
import hello.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;

/**
 * // 这个用来做界面的。
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/monitor") // This means URL's start with /demo (after Application path)
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping(path = "/addMonitor") // Map ONLY GET Requests
    public @ResponseBody
    String addMonitor(@RequestParam String title
            , @RequestParam String url, @RequestParam String mailTo, @RequestParam String monitorField) {
        MonitorInfo monitorInfo = new MonitorInfo();
        monitorInfo.setGmtCreate(Calendar.getInstance().getTime());
        monitorInfo.setTitle(title);
        monitorInfo.setUrl(url);
        monitorInfo.setMailTo(mailTo);
        monitorInfo.setMonitorField(monitorField);
        monitorInfo.setStatus("online");
        monitorService.save(monitorInfo);

        return "200";
    }

    @GetMapping(path = "/addMonitor.html")
    public String checkIfCanAddBanner() {
        // checkLogin
        return "addMonitor";
    }

    @GetMapping(path = "/listMonitor") // Map ONLY GET Requests
    public @ResponseBody
    List<MonitorInfo> listAddBanner() {
        return monitorService.findAll();
    }


//    @GetMapping(path = "/firebase") // Map ONLY GET Requests
//    public @ResponseBody
//    String listFirebase() {
//        // create the firebase
//        // Firebase firebase = new Firebase( your_firebase_workspace_url );
//
//        // exclude .json extension if it's not needed (ie, working with Firebase Authentication REST API)
//        Firebase firebase = new Firebase(your_firebase_workspace_url, false);
//
//        // PUT a map of some data into the firebase
//        Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
//        dataMap.put("PUT", "This was PUT into your workspace root");
//        Firebase response = firebase.getRoot();
//
//        // GET the map back out of the firebase
//        response = firebase.getRoot();
//        System.out.println(response);
//
//        // alternatively, you can get a few details about the response
////        response.getSuccess();    // true/false if method finished successfully
////        response.getCode();    // http-code of method-request
////        response.getBody();    // a map of the data returned
////        response.getRawBody();    // the data returned in it's raw-form (ie: JSON)
//
//        // another alternative, you can PUT/POST your own JSON if you want
//        firebase.setValue("PUT2", "{ 'key': 'Some value' }");
//        return "200";
//    }


    @GetMapping(path = "/deleteMonitor") // Map ONLY GET Requests
    public @ResponseBody
    String listAddBanner(@RequestParam Long id) {
        monitorService.deleteById(id);
        return "200";
    }

}
