package Task2;
// Generated from D:/Desktop/ANTLR4_test/ANTLR4_test/src/PL0.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PL0Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PROGRAM=1, BEGIN=2, END=3, CONST=4, VAR=5, WHILE=6, DO=7, IF=8, THEN=9, 
		IDENTIFIER=10, INTEGER=11, ASSIGN=12, PLUS=13, MINUS=14, MULTIPLY=15, 
		DIVIDE=16, EQUAL=17, NOT_EQUAL=18, LESS_THAN=19, LESS_THAN_OR_EQUAL=20, 
		GREATER_THAN=21, GREATER_THAN_OR_EQUAL=22, LPAREN=23, RPAREN=24, SEMICOLON=25, 
		COMMA=26, WS=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PROGRAM", "BEGIN", "END", "CONST", "VAR", "WHILE", "DO", "IF", "THEN", 
			"IDENTIFIER", "INTEGER", "ASSIGN", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", 
			"EQUAL", "NOT_EQUAL", "LESS_THAN", "LESS_THAN_OR_EQUAL", "GREATER_THAN", 
			"GREATER_THAN_OR_EQUAL", "LPAREN", "RPAREN", "SEMICOLON", "COMMA", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'PROGRAM'", "'BEGIN'", "'END'", "'CONST'", "'VAR'", "'WHILE'", 
			"'DO'", "'IF'", "'THEN'", null, null, "':='", "'+'", "'-'", "'*'", "'/'", 
			"'='", "'<>'", "'<'", "'<='", "'>'", "'>='", "'('", "')'", "';'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PROGRAM", "BEGIN", "END", "CONST", "VAR", "WHILE", "DO", "IF", 
			"THEN", "IDENTIFIER", "INTEGER", "ASSIGN", "PLUS", "MINUS", "MULTIPLY", 
			"DIVIDE", "EQUAL", "NOT_EQUAL", "LESS_THAN", "LESS_THAN_OR_EQUAL", "GREATER_THAN", 
			"GREATER_THAN_OR_EQUAL", "LPAREN", "RPAREN", "SEMICOLON", "COMMA", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public PL0Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PL0.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001b\u0099\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0005\tg\b\t\n\t\f\tj\t\t\u0001\n\u0004"+
		"\nm\b\n\u000b\n\f\nn\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0004"+
		"\u001a\u0094\b\u001a\u000b\u001a\f\u001a\u0095\u0001\u001a\u0001\u001a"+
		"\u0000\u0000\u001b\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017/\u00181\u00193\u001a5\u001b\u0001\u0000\u0004\u0002\u0000"+
		"AZaz\u0003\u000009AZaz\u0001\u000009\u0003\u0000\t\n\r\r  \u009b\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00017\u0001\u0000\u0000\u0000"+
		"\u0003?\u0001\u0000\u0000\u0000\u0005E\u0001\u0000\u0000\u0000\u0007I"+
		"\u0001\u0000\u0000\u0000\tO\u0001\u0000\u0000\u0000\u000bS\u0001\u0000"+
		"\u0000\u0000\rY\u0001\u0000\u0000\u0000\u000f\\\u0001\u0000\u0000\u0000"+
		"\u0011_\u0001\u0000\u0000\u0000\u0013d\u0001\u0000\u0000\u0000\u0015l"+
		"\u0001\u0000\u0000\u0000\u0017p\u0001\u0000\u0000\u0000\u0019s\u0001\u0000"+
		"\u0000\u0000\u001bu\u0001\u0000\u0000\u0000\u001dw\u0001\u0000\u0000\u0000"+
		"\u001fy\u0001\u0000\u0000\u0000!{\u0001\u0000\u0000\u0000#}\u0001\u0000"+
		"\u0000\u0000%\u0080\u0001\u0000\u0000\u0000\'\u0082\u0001\u0000\u0000"+
		"\u0000)\u0085\u0001\u0000\u0000\u0000+\u0087\u0001\u0000\u0000\u0000-"+
		"\u008a\u0001\u0000\u0000\u0000/\u008c\u0001\u0000\u0000\u00001\u008e\u0001"+
		"\u0000\u0000\u00003\u0090\u0001\u0000\u0000\u00005\u0093\u0001\u0000\u0000"+
		"\u000078\u0005P\u0000\u000089\u0005R\u0000\u00009:\u0005O\u0000\u0000"+
		":;\u0005G\u0000\u0000;<\u0005R\u0000\u0000<=\u0005A\u0000\u0000=>\u0005"+
		"M\u0000\u0000>\u0002\u0001\u0000\u0000\u0000?@\u0005B\u0000\u0000@A\u0005"+
		"E\u0000\u0000AB\u0005G\u0000\u0000BC\u0005I\u0000\u0000CD\u0005N\u0000"+
		"\u0000D\u0004\u0001\u0000\u0000\u0000EF\u0005E\u0000\u0000FG\u0005N\u0000"+
		"\u0000GH\u0005D\u0000\u0000H\u0006\u0001\u0000\u0000\u0000IJ\u0005C\u0000"+
		"\u0000JK\u0005O\u0000\u0000KL\u0005N\u0000\u0000LM\u0005S\u0000\u0000"+
		"MN\u0005T\u0000\u0000N\b\u0001\u0000\u0000\u0000OP\u0005V\u0000\u0000"+
		"PQ\u0005A\u0000\u0000QR\u0005R\u0000\u0000R\n\u0001\u0000\u0000\u0000"+
		"ST\u0005W\u0000\u0000TU\u0005H\u0000\u0000UV\u0005I\u0000\u0000VW\u0005"+
		"L\u0000\u0000WX\u0005E\u0000\u0000X\f\u0001\u0000\u0000\u0000YZ\u0005"+
		"D\u0000\u0000Z[\u0005O\u0000\u0000[\u000e\u0001\u0000\u0000\u0000\\]\u0005"+
		"I\u0000\u0000]^\u0005F\u0000\u0000^\u0010\u0001\u0000\u0000\u0000_`\u0005"+
		"T\u0000\u0000`a\u0005H\u0000\u0000ab\u0005E\u0000\u0000bc\u0005N\u0000"+
		"\u0000c\u0012\u0001\u0000\u0000\u0000dh\u0007\u0000\u0000\u0000eg\u0007"+
		"\u0001\u0000\u0000fe\u0001\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000"+
		"hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000i\u0014\u0001\u0000"+
		"\u0000\u0000jh\u0001\u0000\u0000\u0000km\u0007\u0002\u0000\u0000lk\u0001"+
		"\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000"+
		"no\u0001\u0000\u0000\u0000o\u0016\u0001\u0000\u0000\u0000pq\u0005:\u0000"+
		"\u0000qr\u0005=\u0000\u0000r\u0018\u0001\u0000\u0000\u0000st\u0005+\u0000"+
		"\u0000t\u001a\u0001\u0000\u0000\u0000uv\u0005-\u0000\u0000v\u001c\u0001"+
		"\u0000\u0000\u0000wx\u0005*\u0000\u0000x\u001e\u0001\u0000\u0000\u0000"+
		"yz\u0005/\u0000\u0000z \u0001\u0000\u0000\u0000{|\u0005=\u0000\u0000|"+
		"\"\u0001\u0000\u0000\u0000}~\u0005<\u0000\u0000~\u007f\u0005>\u0000\u0000"+
		"\u007f$\u0001\u0000\u0000\u0000\u0080\u0081\u0005<\u0000\u0000\u0081&"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\u0005<\u0000\u0000\u0083\u0084\u0005"+
		"=\u0000\u0000\u0084(\u0001\u0000\u0000\u0000\u0085\u0086\u0005>\u0000"+
		"\u0000\u0086*\u0001\u0000\u0000\u0000\u0087\u0088\u0005>\u0000\u0000\u0088"+
		"\u0089\u0005=\u0000\u0000\u0089,\u0001\u0000\u0000\u0000\u008a\u008b\u0005"+
		"(\u0000\u0000\u008b.\u0001\u0000\u0000\u0000\u008c\u008d\u0005)\u0000"+
		"\u0000\u008d0\u0001\u0000\u0000\u0000\u008e\u008f\u0005;\u0000\u0000\u008f"+
		"2\u0001\u0000\u0000\u0000\u0090\u0091\u0005,\u0000\u0000\u00914\u0001"+
		"\u0000\u0000\u0000\u0092\u0094\u0007\u0003\u0000\u0000\u0093\u0092\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0093\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0097\u0001"+
		"\u0000\u0000\u0000\u0097\u0098\u0006\u001a\u0000\u0000\u00986\u0001\u0000"+
		"\u0000\u0000\u0004\u0000hn\u0095\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}