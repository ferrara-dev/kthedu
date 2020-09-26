package Assignment1;

public class TextFilter {

    public TextFilter() {

    }

    public String filterText(String text) {
        String output = "";
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            if (isAlpha(current)) {
                output += current;
            } else {
                output += " ";
            }
        }
        return output;
    }

    private boolean isAlpha(char c) {
        if (c == '\n' || c == ' ')
            return true;
        if (c >= 'a' && c <= 'z')
            return true;
        if (c >= 'A' && c <= 'Z')
            return true;
        return false;
    }

    private static class TextFilterTester{
        public static void main(String...args){
            String testString = "Hello my name4is.SomeThing";
            System.out.println("Before filtering -> " + testString);
            TextFilter textFilter = new TextFilter();
            testString  = textFilter.filterText(testString);
            System.out.println("After filtering -> " + testString);
        }
    }
}
