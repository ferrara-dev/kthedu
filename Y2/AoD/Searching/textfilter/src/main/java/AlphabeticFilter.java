public class AlphabeticFilter implements TextFilter{

    @Override
    public String filter(String input) {
        return input.replaceAll("[^a-zA-Z]"," ");
    }

    public static class FilterTester{
        public static void main(String...args){
            String input = "hello 123 h3llo";
            System.out.println(input);
            AlphabeticFilter alphabeticFilter = new AlphabeticFilter();
            System.out.println(alphabeticFilter.filter(input));

        }
    }
}
