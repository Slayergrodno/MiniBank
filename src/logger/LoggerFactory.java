package logger;

public class LoggerFactory {
    public static Logger getInstancy(Class clazz){
        return new FileLogger(clazz);
    }
}
