import java.util.*;

class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String e : emails) {
            String[] parts = e.split("@");
            String local = parts[0].split("\\+")[0].replace(".", "");
            set.add(local + "@" + parts[1]);
        }
        return set.size();
    }
}