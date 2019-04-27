public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        // TODO: binary search!

       long lastPass = 0;
       long lastFail = n;
       long i;

       while (lastFail != lastPass+1){
           i = (lastFail - lastPass)/2 + lastPass;
           if (isBadVersion.isFailingVersion(i)){
               lastFail = i;
           }
           else if (!isBadVersion.isFailingVersion(i)){
               lastPass = i;
           }
       }

        return lastFail;
    }

    public static long firstBadVersionLinearSearch(long n, IsFailingVersion isBadVersion) {
        // TODO

        for (long i = 0; i < n; i++){
            if (isBadVersion.isFailingVersion(i)){
                return i;
            }
        }
        return -1;
    }
}
