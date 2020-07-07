package ee.ristoseene.raytracer.eyebased.demo.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public final class BackgroundExecutor {

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor(new BackgroundThreadFactory());

    static class BackgroundThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "Background thread");
            thread.setPriority(Thread.NORM_PRIORITY);
            thread.setDaemon(true);
            return thread;
        }

    }

}
