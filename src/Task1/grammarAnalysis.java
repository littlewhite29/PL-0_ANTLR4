import java.util.*;

public class grammarAnalysis {

    private List<token> inputTokens;
    private int currentTokenIndex;
    private List<String> intermediateCode;

    public grammarAnalysis(List<token> tokens) {
        this.inputTokens = tokens;
        this.currentTokenIndex = 0;
        this.intermediateCode = new ArrayList<>();
    }

    private String getNextToken() {
        if (currentTokenIndex < inputTokens.size()) {
            return inputTokens.get(currentTokenIndex++);
        }
        return null;
    }

    private void match(tokenEnum expectedTokenEnum) throws ParseException {
        token actualToken = getNextToken();
        if (actualToken == null || !actualToken.tokenEnum.equals(expectedTokenEnum)) {
            throw new ParseException("Syntax error: Expected '" + expectedToken.tokenEnum + "', but found '" + actualToken.tokenEnum + "'");
        }
    }

    public void parseProgram() throws ParseException {
        parseProgramHeader();
        parseSubprogram();
        System.out.println("Parsing successful.");
        System.out.println("Intermediate Code:");
        for (String code : intermediateCode) {
            System.out.println(code);
        }
    }

    private void parseProgramHeader() throws ParseException {
        match("PROGRAM");
        Token = getNextToken();
        intermediateCode.add("("+"PROGRAM" +","+ programName);
    }

    private void parseSubprogram() throws ParseException {
        parseOptionalConstantDeclaration();
        parseOptionalVariableDeclaration();
        parseStatement();
    }

    private void parseOptionalConstantDeclaration() throws ParseException {
        if (getNextToken().equals("CONST")) {
            do {
                parseConstantDefinition();
            } while (getNextToken().equals(","));
            match(";");
        }
    }

    private void parseConstantDefinition() throws ParseException {
        String identifier = getNextToken();
        match(":=");
        String value = getNextToken();
        intermediateCode.add("CONST " + identifier + " " + value);
    }

    private void parseOptionalVariableDeclaration() throws ParseException {
        if (getNextToken().equals("VAR")) {
            do {
                parseVariableDeclaration();
            } while (getNextToken().equals(","));
            match(";");
        }
    }

    private void parseVariableDeclaration() throws ParseException {
        String identifier = getNextToken();
        intermediateCode.add("VAR " + identifier);
    }

    private void parseStatement() throws ParseException {
        String nextToken = getNextToken();
        switch (nextToken) {
            case "BEGIN":
                parseCompoundStatement();
                break;
            case "IF":
                parseConditionalStatement();
                break;
            case "WHILE":
                parseWhileStatement();
                break;
            case "IDENTIFIER":
                parseAssignmentStatement();
                break;
            // Add cases for other statement types as needed
            default:
                // Handle error or empty statement
        }
    }

    private void parseCompoundStatement() throws ParseException {
        match("BEGIN");
        do {
            parseStatement();
        } while (getNextToken().equals(";"));
        match("END");
    }

    private void parseAssignmentStatement() throws ParseException {
        String identifier = getNextToken();
        match(":=");
        String expression = parseExpression();
        intermediateCode.add("ASSIGN " + identifier + " " + expression);
    }

    private String parseExpression() throws ParseException {
        // Implement the parsing logic for expressions
        // Return the result as a string for simplicity
        return "";
    }

    // Implement other parsing methods for different statement types as needed

    public static void main(String[] args) {
        List<String> tokens = Arrays.asList("PROGRAM", "MyProgram", "VAR", "x", ",", "y", ";", "BEGIN", "x", ":=", "5", ";", "END", ".");
        LL1Parser parser = new LL1Parser(tokens);
        try {
            parser.parseProgram();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static class ParseException extends Exception {
        public ParseException(String message) {
            super(message);
        }
    }
}
