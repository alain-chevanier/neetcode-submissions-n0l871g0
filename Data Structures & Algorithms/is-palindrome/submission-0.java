class Solution {
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int beg = 0, end = chars.length-1;
        String regex = "[^0-9a-zA-Z]";
        while (beg < end) {
            while (beg < end && String.valueOf(chars[beg]).matches(regex)) {
                beg++;
            }
            while (beg < end && String.valueOf(chars[end]).matches(regex)) {
                end--;
            }
            if (Character.toLowerCase(chars[beg++]) != Character.toLowerCase(chars[end--])) {
                return false;
            }
        }
        return true;
    }
}
