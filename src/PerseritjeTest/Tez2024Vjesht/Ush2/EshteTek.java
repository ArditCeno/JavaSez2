// 2. (14 pikë) Ndërtoni metodën ështëTek që merr si parametër numrin n dhe kthen true nëse
// shifrat në pozicionet tek janë numra tek dhe false në të kundërt. Për shembull metoda që merr
// si parametër numrin 347452 do të kthejë true.

// 2. (15 pikë) Ndërtoni metodën reverseCharactersInWords që merr si parametër një string "fjali"
// dhe kthen një string të ri ku karakteret brenda çdo fjale janë kthyer mbrapsht, por rendi i
// fjalëve mbetet i njëjtë. Për shembull, metoda që merr si parametër string-un "hello world"
// duhet të kthejë "olleh dlrow".

public class EshteTek {
    public static boolean eshteTek(int n) {
        String s = Integer.toString(n);
        for (int i = 0; i < s.length(); i++) {
            if ((i + 1) % 2 == 1) {
                int digit = s.charAt(i) - '0';
                if (digit % 2 == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String reverseCharactersInWords(String fjali) {
        String[] words = fjali.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String reversed = new StringBuilder(words[i]).reverse().toString();
            result.append(reversed);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
