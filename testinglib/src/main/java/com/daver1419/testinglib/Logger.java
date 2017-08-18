package com.daver1419.testinglib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class Logger {

    private static String tag = "Logger";
    private static Logger instance;

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    /**
     * This is only use for Test cases
     *
     * @param instance
     */
    public static void setInstance(final Logger instance) {
        Logger.instance = instance;
    }


    public void debug(String message, Context context) {
        debug(message, false, context);
    }

    public void debug(final String message, boolean displayToast, final Context context) {
        if (isDebugBuild()) {
            Log.d(tag, message);

            /*if (remoteLogger != null) {
                final Runnable loggerRemoteLogger = new Runnable() {
                    @Override
                    public void run() {
                        com.qa_wall_logger_client.log.Log log = new com.qa_wall_logger_client.log.Log(
                                UUID.randomUUID().toString(), ILog.Type.EVENT,
                                System.currentTimeMillis(), message, AndroidUtils.getDeviceID());
                        remoteLogger.send(log);
                    }
                };

                LoggerHelper.getExecutor().execute(loggerRemoteLogger);
            }*/

            if (displayToast) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isDebugBuild() {
        //TODO
        return true;
    }

    public void info(String info) {
        Log.i(tag, info);
    }

    public void warning(String message) {
        Log.w(tag, message);
    }

    public void error(String error) {
        error(error, null, false);
    }

    public void error(String error, @Nullable Exception e) {
        error(error, e, false);
    }


    public void error(final String error, final @Nullable Exception e, boolean sendToAnalytics) {
        Log.e(tag, error + "\n");

        if (e != null) {
            e.printStackTrace();
        }

        if (sendToAnalytics) {     //TODO Add analytics
//            AnalyticsUtil.get().sendHandledException(error, e);
        }
    }
}