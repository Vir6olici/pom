package gateway.gateway.post;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class SimplePostService extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(SimplePostService.class);

    @Override
    public String filterType() { return "post"; }

    @Override
    public int filterOrder() { return 2; }

    @Override
    public boolean shouldFilter() { return true; }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if(request.getRequestURL().toString().contains("insert")){
            Map<String,List<String>> requestParams = ctx.getRequestQueryParams();
            String asString =  request.getRequestURL().toString();
            List<String> listStringFirstname = requestParams.get("firstname");
            List<String> listStringLastname = requestParams.get("lastname");
            if(listStringLastname.get(0).toString().equals("") && listStringFirstname.get(0).toString().equals("")){
                log.info(String.format("%s filterType:POST request to %s but the params are null", request.getMethod(), request.getRequestURL().toString()));

                throw new RuntimeException("The Querry params are null");
            }else if(listStringLastname.get(0).equals("")){
                log.info(String.format("%s filterType:POST request to %s but the param lastname is null", request.getMethod(), request.getRequestURL().toString()));
                throw new RuntimeException("The Querry param Lastname is null");
            }else if(listStringFirstname.get(0).equals("")) {
                log.info(String.format("%s filterType:POST request to %s but the param firstname is null", request.getMethod(), request.getRequestURL().toString()));
                throw new RuntimeException("The Querry param firstname is null");
            }else{
                log.info(String.format("%s filterType:POST request to %s " + requestParams.toString(), request.getMethod(), request.getRequestURL().toString()));
            }
        }else{
            log.info(String.format("%s filterType:POST request to %s", request.getMethod(), request.getRequestURL().toString()));
        }
        return null;
    }
}