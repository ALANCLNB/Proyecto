package sample.Constains;

import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Configs {
    public static final String Username="";
    public static final String[] KEYWORDS = new String[] {
            "String","Int","Double","SI","SINO","SEGUN","MIENTRAS","PARA","HACER","HASTA","OPCION 1","OPCION 2","OPCION 3"
    };

    public static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    public static final String PAREN_PATTERN = "\\(|\\)";
    public static final String BRACE_PATTERN = "\\{|\\}";
    public static final String BRACKET_PATTERN = "\\[|\\]";
    public static final String SEMICOLON_PATTERN = "\\;";
    public static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    public static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

    public static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );

    public static final String sampleCode = String.join("\n", new String[] {
            "String x",
            "Int y",
            "Double b",
            "SI(x=5)HACER{hola}",
            "SI(x=5) HACER{hola}SINO{HELLO}",
            "SI(x>=5)HACER {hola}SINO{HELLO}",
            "SI(x<=5) HACER{hola}SINO{HELLO}",
            "SI (x<=5)   HACER   {hola}SINO{HELLO}",
            "SI" +
                    "(x<=5)HACER{hola}SINO{HELLO}",


            "SI(X=5)",
            "SI(X=5)   HACER",
            "{HOLA}",
            "{HOLA",
            "{",
            "HOLA",
            "}",
            "",
            "SEGUN (X)",
            "HACER OPCION 1",
            "HACER OPCION 2",
            "HACER",
            "HACER{",
            "OPCION 3",
            "OPCION 3{HELLO}",
            "OPCION 3{",
            "OPCION 3{HOLA",


            "PARA (X=120)",
            "PARA (X=120)HASTA (X=769)",
            "HASTA (X=480)",
            "HASTA (X=480) HACER",
            "HASTA (X=480) HACER{",
            "HASTA (X=480) HACER{NSL}",
            "HASTA (X=480) HACER{NSL",
            "MIENTRAS (X=90)",



            "SEGUN (x) HACER OPCION 1 {HOLA} OPCION 2 {HELLO} OPCION 3 {VONYOUR}",

            "PARA (M=100) HASTA (M<=150) HACER {NSL}",
            "MIENTRAS (X=15b)  HACER  {HOLA}"
    });

    public static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    public static String [] EXPRESIONES ={
            "String([\\s]+)?[A-Za-z]+([\\s]+)?",
            "Int([\\s]+)?[A-Za-z]+([\\s]+)?",
            "Double([\\s]+)?[A-Za-z]+([\\s]+)?",


            "SI([\\s])?[(]\\w+([=!<>]||>=||<=||)\\w+[)]([\\s]+)?HACER([\\s]+)?[{]([\\s]+)?\\w+([\\s]+)?[}]([\\s]+)?(SINO([\\s]+)?[{]([\\s]+)?\\w+([\\s]+)?[}]?)?([\\s]+)?",
            "SINO([\\s])?[{]([\\s]+)?\\w+([\\s]+)?[}]?([\\s]+)?",
            "SI([\\s])?[(]\\w+([=!<>]||>=||<=||)\\w+[)]([\\s]+)?HACER([\\s]+)?",
            "SEGUN([\\s]+)?[(]\\w+[)]([\\s]+)?HACER[\\s]+" +
                    "OPCION 1([\\s]+)?[{]([\\s]+)?\\w+([\\s]+)?[}]([\\s]+)?" +
                    "(OPCION 2([\\s]+)?[{]([\\s]+)?\\w+([\\s]+)?[}])?([\\s]+)?" +
                    "(OPCION 3([\\s]+)?[{]([\\s]+)?\\w+([\\s]+)?[}])?([\\s]+)?" +
                    "(SINO[\\s]+[{]([\\s]+)?\\w+([\\s]+)?[}])?([\\s]+)?",

            //"PARA([\\s])?[(]\\w+=\\d+[)]([\\s]+)?(HASTA([\\s]{1})?[(]\\w+(=||<=||<||>=||>)\\d+[)]([\\s])?HACER([\\s]+)?[{]?[\\w+]?[}]?([\\s]+)?)?",

            "MIENTRAS[\\s]+[(]\\w+(=||<=||>=||=!||<||>)\\w+[)]([\\s]+)?(HACER)?(([\\s]+)?[{]([\\s]+)?\\w+([\\s]+)?[}]([\\s]+)?)?",




            "SI",
            "SI([\\s]+)?[(]\\w+([=!<>]||>=||<=||)\\w+[)]([\\s]+)?(HACER)?([\\s]+)?",
            "[{]?\\w+[}]?([\\s]+)?",
            "([\\s]+)?([{]||[}])([\\s]+)?",
            "SINO ([\\s]+)?[{]?(\\w+)?[}]([\\s]+)??",

            "SEGUN ([\\s]+)?[(]([\\s]+)?\\w+([\\s]+)?[)]([\\s]+)?",
            "HACER([\\s]+)?(OPCION[\\s]{1}[123])?[{]?([\\s]+)?",
            "OPCION [123]([\\s]+)?[{]?([\\s]+)?",
            "OPCION [123]([\\s]+)?[{]([\\s]+)?(\\w+)?([\\s]+)?[}]?([\\s]+)?",
            "HACER([\\s]+)?[{]([\\s]+)?([\\s]+)?",
            "HACER([\\s]+)?[{]([\\s]+)?(\\w+)?([\\s]+)?",
            "HACER([\\s]+)?([{])([\\s]+)?\\w+([\\s]+)?[}]([\\s]+)??(SINO([\\s]+)?[{]([\\s]+)?\\w+([\\s]+)?[}]?([\\s]+)?)",

            "PARA [(]\\w+([=!<>]||>=||<=)\\w+[)]([\\s]+)?(HASTA [(]\\w+([=!<>]||>=||<=)\\w+[)])?([\\s]+)?(HACER([\\s]+)?[{]([\\s]+)?(\\w+)([\\s]+)?[}])?",


            "HASTA([\\s])?[(]\\w+([=!<>]||>=||<=)\\w+[)]([\\s]+)?" +
                    "(HACER([\\s]+)?[{]([\\s]+)?[\\w+]([\\s]+)?[}])?([\\s]+)?" +
                    "(HACER([\\s]+)?[{]?([\\s]+)?)?" +
                    "(HACER([\\s]+)?[{]([\\s]+)?(\\w+)?)?([\\s]+)?" +
                    "(HACER([\\s]+)?[{]([\\s]+)?\\w+([\\s]+)?[}]?)?([\\s]+)?",


                    "([\\s]+)?[{]?([\\s]+)?(\\w+)([\\s]+)?[}]?([\\s]+)?",
            "MIENTRAS [(]\\w+([=!<>]||>=||<=)\\w+[)]"
    };
}

//([\s]+)?[)]([\s]+)?}