package mk.ukim.finki.emt.onlinelibrary.sharedkernel.infra.eventlog;


import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.RemoteEventLog;
import org.springframework.stereotype.Component;

@Component
public interface RemoteEventLogService {

    String source();

    RemoteEventLog currentLog();

}
