class Solution {

    public String minRemoveToMakeValid(String s) {

        StringBuilder sb = new StringBuilder();
        int open = 0;

        // Remove extra ')'
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                open++;
                sb.append(ch);
            }
            else if (ch == ')') {
                if (open > 0) {
                    open--;
                    sb.append(ch);
                }
            }
            else {
                sb.append(ch);
            }
        }

        // Remove extra '(' from right
        StringBuilder result = new StringBuilder();
        int removeOpen = open;

        for (int i = sb.length() - 1; i >= 0; i--) {
            char ch = sb.charAt(i);

            if (ch == '(' && removeOpen > 0) {
                removeOpen--;
                continue;
            }
            result.append(ch);
        }

        return result.reverse().toString();
    }
}