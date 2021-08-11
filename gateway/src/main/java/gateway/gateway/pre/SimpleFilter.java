package gateway.gateway.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class SimpleFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() { // aici se suprascrie sa faca ceva "frumos"
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestAsString = request.getRequestURL().toString();
        boolean admin = requestAsString.contains("admin");
        if (admin) {
            throw new RuntimeException("Acces blocat la resursa");
        }
        log.info(String.format("%s filterType:PRE request to %s", request.getMethod(), request.getRequestURL().toString()));

        return null;
    }
    // de implementat post filter

}
