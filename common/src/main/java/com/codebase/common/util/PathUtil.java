package com.codebase.common.util;

import java.text.MessageFormat;

public class PathUtil {

    public static String parseTopicFromZkPath(String topicPath) {
        return topicPath.substring(topicPath.lastIndexOf("/") + 1);
    }

    public static short parsePartitionIdFromZkPath(String partitionPath) {
        return Short.parseShort(partitionPath.substring(partitionPath.lastIndexOf("/") + 1));
    }

    public static String getLocksPath() {
        return getPath(Path.LOCKS);
    }

    public static String getFollowsLockPath(String cluster) {
        return getPath(Path.LOCKS_FOLLOWS, cluster);
    }

    public static String getFollowLockPath(String cluster, String slice) {
        return getPath(Path.LOCKS_FOLLOW, cluster, slice);
    }

    private static String getPath(Path path, Object... args) {
        return path.get(args);
    }

    public static String ROOT = "/zk";

    public enum Path {
        LOCKS(PathRoot.LOCKS, "", 0), //
        LOCKS_FOLLOWS(PathRoot.LOCKS, "/{0}", 1), //
        LOCKS_FOLLOW(PathRoot.LOCKS, "/{0}/{1}", 2), //
        //
        ;

        private PathRoot root;
        private String fmt;
        private int args;

        Path(PathRoot root, String fmt, int args) {
            this.root = root;
            this.fmt = fmt;
            this.args = args;
        }

        public String get(Object... args) {
            if (args.length != this.args) {
                throw new RuntimeException("args not match");
            }
            return root.value + MessageFormat.format(fmt, args);
        }

        private enum PathRoot {
            LOCKS(ROOT + "/topics"), //
            //
            ;
            String value;

            PathRoot(String value) {
                this.value = value;
            }
        }
    }
}
