package com.baiji.antlr;
// Generated from BaijiGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class BaijiGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, SINGLE_LINE_COMMENT=23, 
		MULTI_LINE_COMMENT=24, STRING_LITERAL=25, INTEGER_LITERAL=26, IDENTIFIER=27, 
		WS=28;
	public static final int
		RULE_program = 0, RULE_syntaxDeclaration = 1, RULE_packageDeclaration = 2, 
		RULE_appIdDeclaration = 3, RULE_classDeclaration = 4, RULE_fieldDeclaration = 5, 
		RULE_type = 6, RULE_serviceDeclaration = 7, RULE_methodDeclaration = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "syntaxDeclaration", "packageDeclaration", "appIdDeclaration", 
			"classDeclaration", "fieldDeclaration", "type", "serviceDeclaration", 
			"methodDeclaration"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'syntax'", "'='", "';'", "'package'", "'appid'", "'class'", "'{'", 
			"'}'", "'string'", "'int16'", "'int32'", "'float'", "'double'", "'boolean'", 
			"'Map'", "'<'", "','", "'>'", "'List'", "'service'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "SINGLE_LINE_COMMENT", 
			"MULTI_LINE_COMMENT", "STRING_LITERAL", "INTEGER_LITERAL", "IDENTIFIER", 
			"WS"
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

	@Override
	public String getGrammarFileName() { return "BaijiGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BaijiGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public SyntaxDeclarationContext syntaxDeclaration() {
			return getRuleContext(SyntaxDeclarationContext.class,0);
		}
		public PackageDeclarationContext packageDeclaration() {
			return getRuleContext(PackageDeclarationContext.class,0);
		}
		public AppIdDeclarationContext appIdDeclaration() {
			return getRuleContext(AppIdDeclarationContext.class,0);
		}
		public ServiceDeclarationContext serviceDeclaration() {
			return getRuleContext(ServiceDeclarationContext.class,0);
		}
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			syntaxDeclaration();
			setState(19);
			packageDeclaration();
			setState(20);
			appIdDeclaration();
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(21);
				classDeclaration();
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(27);
			serviceDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SyntaxDeclarationContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(BaijiGrammarParser.STRING_LITERAL, 0); }
		public SyntaxDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_syntaxDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).enterSyntaxDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).exitSyntaxDeclaration(this);
		}
	}

	public final SyntaxDeclarationContext syntaxDeclaration() throws RecognitionException {
		SyntaxDeclarationContext _localctx = new SyntaxDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_syntaxDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(T__0);
			setState(30);
			match(T__1);
			setState(31);
			match(STRING_LITERAL);
			setState(32);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PackageDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(BaijiGrammarParser.IDENTIFIER, 0); }
		public PackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).enterPackageDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).exitPackageDeclaration(this);
		}
	}

	public final PackageDeclarationContext packageDeclaration() throws RecognitionException {
		PackageDeclarationContext _localctx = new PackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_packageDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__3);
			setState(35);
			match(IDENTIFIER);
			setState(36);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AppIdDeclarationContext extends ParserRuleContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(BaijiGrammarParser.INTEGER_LITERAL, 0); }
		public AppIdDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_appIdDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).enterAppIdDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).exitAppIdDeclaration(this);
		}
	}

	public final AppIdDeclarationContext appIdDeclaration() throws RecognitionException {
		AppIdDeclarationContext _localctx = new AppIdDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_appIdDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(T__4);
			setState(39);
			match(T__1);
			setState(40);
			match(INTEGER_LITERAL);
			setState(41);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(BaijiGrammarParser.IDENTIFIER, 0); }
		public List<FieldDeclarationContext> fieldDeclaration() {
			return getRuleContexts(FieldDeclarationContext.class);
		}
		public FieldDeclarationContext fieldDeclaration(int i) {
			return getRuleContext(FieldDeclarationContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(T__5);
			setState(44);
			match(IDENTIFIER);
			setState(45);
			match(T__6);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 159972864L) != 0)) {
				{
				{
				setState(46);
				fieldDeclaration();
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(BaijiGrammarParser.IDENTIFIER, 0); }
		public TerminalNode SINGLE_LINE_COMMENT() { return getToken(BaijiGrammarParser.SINGLE_LINE_COMMENT, 0); }
		public TerminalNode MULTI_LINE_COMMENT() { return getToken(BaijiGrammarParser.MULTI_LINE_COMMENT, 0); }
		public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).enterFieldDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).exitFieldDeclaration(this);
		}
	}

	public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
		FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fieldDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SINGLE_LINE_COMMENT || _la==MULTI_LINE_COMMENT) {
				{
				setState(54);
				_la = _input.LA(1);
				if ( !(_la==SINGLE_LINE_COMMENT || _la==MULTI_LINE_COMMENT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(57);
			type();
			setState(58);
			match(IDENTIFIER);
			setState(59);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(BaijiGrammarParser.IDENTIFIER, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		try {
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(T__8);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				match(T__9);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				match(T__10);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(64);
				match(T__11);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(65);
				match(T__12);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 6);
				{
				setState(66);
				match(T__13);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 7);
				{
				setState(67);
				match(T__14);
				setState(68);
				match(T__15);
				setState(69);
				type();
				setState(70);
				match(T__16);
				setState(71);
				type();
				setState(72);
				match(T__17);
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 8);
				{
				setState(74);
				match(T__18);
				setState(75);
				match(T__15);
				setState(76);
				type();
				setState(77);
				match(T__17);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 9);
				{
				setState(79);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ServiceDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(BaijiGrammarParser.IDENTIFIER, 0); }
		public List<MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public ServiceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).enterServiceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).exitServiceDeclaration(this);
		}
	}

	public final ServiceDeclarationContext serviceDeclaration() throws RecognitionException {
		ServiceDeclarationContext _localctx = new ServiceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_serviceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__19);
			setState(83);
			match(IDENTIFIER);
			setState(84);
			match(T__6);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 134807040L) != 0)) {
				{
				{
				setState(85);
				methodDeclaration();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodDeclarationContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(BaijiGrammarParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(BaijiGrammarParser.IDENTIFIER, i);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaijiGrammarListener ) ((BaijiGrammarListener)listener).exitMethodDeclaration(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_methodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			type();
			setState(94);
			match(IDENTIFIER);
			setState(95);
			match(T__20);
			setState(96);
			type();
			setState(97);
			match(IDENTIFIER);
			setState(98);
			match(T__21);
			setState(99);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001cf\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000"+
		"\u0017\b\u0000\n\u0000\f\u0000\u001a\t\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005"+
		"\u00040\b\u0004\n\u0004\f\u00043\t\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0003\u00058\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006Q\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0005\u0007W\b\u0007\n\u0007\f\u0007Z\t\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0000\u0000\t\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0000\u0001\u0001\u0000\u0017\u0018h\u0000\u0012\u0001\u0000\u0000\u0000"+
		"\u0002\u001d\u0001\u0000\u0000\u0000\u0004\"\u0001\u0000\u0000\u0000\u0006"+
		"&\u0001\u0000\u0000\u0000\b+\u0001\u0000\u0000\u0000\n7\u0001\u0000\u0000"+
		"\u0000\fP\u0001\u0000\u0000\u0000\u000eR\u0001\u0000\u0000\u0000\u0010"+
		"]\u0001\u0000\u0000\u0000\u0012\u0013\u0003\u0002\u0001\u0000\u0013\u0014"+
		"\u0003\u0004\u0002\u0000\u0014\u0018\u0003\u0006\u0003\u0000\u0015\u0017"+
		"\u0003\b\u0004\u0000\u0016\u0015\u0001\u0000\u0000\u0000\u0017\u001a\u0001"+
		"\u0000\u0000\u0000\u0018\u0016\u0001\u0000\u0000\u0000\u0018\u0019\u0001"+
		"\u0000\u0000\u0000\u0019\u001b\u0001\u0000\u0000\u0000\u001a\u0018\u0001"+
		"\u0000\u0000\u0000\u001b\u001c\u0003\u000e\u0007\u0000\u001c\u0001\u0001"+
		"\u0000\u0000\u0000\u001d\u001e\u0005\u0001\u0000\u0000\u001e\u001f\u0005"+
		"\u0002\u0000\u0000\u001f \u0005\u0019\u0000\u0000 !\u0005\u0003\u0000"+
		"\u0000!\u0003\u0001\u0000\u0000\u0000\"#\u0005\u0004\u0000\u0000#$\u0005"+
		"\u001b\u0000\u0000$%\u0005\u0003\u0000\u0000%\u0005\u0001\u0000\u0000"+
		"\u0000&\'\u0005\u0005\u0000\u0000\'(\u0005\u0002\u0000\u0000()\u0005\u001a"+
		"\u0000\u0000)*\u0005\u0003\u0000\u0000*\u0007\u0001\u0000\u0000\u0000"+
		"+,\u0005\u0006\u0000\u0000,-\u0005\u001b\u0000\u0000-1\u0005\u0007\u0000"+
		"\u0000.0\u0003\n\u0005\u0000/.\u0001\u0000\u0000\u000003\u0001\u0000\u0000"+
		"\u00001/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000024\u0001\u0000"+
		"\u0000\u000031\u0001\u0000\u0000\u000045\u0005\b\u0000\u00005\t\u0001"+
		"\u0000\u0000\u000068\u0007\u0000\u0000\u000076\u0001\u0000\u0000\u0000"+
		"78\u0001\u0000\u0000\u000089\u0001\u0000\u0000\u00009:\u0003\f\u0006\u0000"+
		":;\u0005\u001b\u0000\u0000;<\u0005\u0003\u0000\u0000<\u000b\u0001\u0000"+
		"\u0000\u0000=Q\u0005\t\u0000\u0000>Q\u0005\n\u0000\u0000?Q\u0005\u000b"+
		"\u0000\u0000@Q\u0005\f\u0000\u0000AQ\u0005\r\u0000\u0000BQ\u0005\u000e"+
		"\u0000\u0000CD\u0005\u000f\u0000\u0000DE\u0005\u0010\u0000\u0000EF\u0003"+
		"\f\u0006\u0000FG\u0005\u0011\u0000\u0000GH\u0003\f\u0006\u0000HI\u0005"+
		"\u0012\u0000\u0000IQ\u0001\u0000\u0000\u0000JK\u0005\u0013\u0000\u0000"+
		"KL\u0005\u0010\u0000\u0000LM\u0003\f\u0006\u0000MN\u0005\u0012\u0000\u0000"+
		"NQ\u0001\u0000\u0000\u0000OQ\u0005\u001b\u0000\u0000P=\u0001\u0000\u0000"+
		"\u0000P>\u0001\u0000\u0000\u0000P?\u0001\u0000\u0000\u0000P@\u0001\u0000"+
		"\u0000\u0000PA\u0001\u0000\u0000\u0000PB\u0001\u0000\u0000\u0000PC\u0001"+
		"\u0000\u0000\u0000PJ\u0001\u0000\u0000\u0000PO\u0001\u0000\u0000\u0000"+
		"Q\r\u0001\u0000\u0000\u0000RS\u0005\u0014\u0000\u0000ST\u0005\u001b\u0000"+
		"\u0000TX\u0005\u0007\u0000\u0000UW\u0003\u0010\b\u0000VU\u0001\u0000\u0000"+
		"\u0000WZ\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000XY\u0001\u0000"+
		"\u0000\u0000Y[\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000[\\\u0005"+
		"\b\u0000\u0000\\\u000f\u0001\u0000\u0000\u0000]^\u0003\f\u0006\u0000^"+
		"_\u0005\u001b\u0000\u0000_`\u0005\u0015\u0000\u0000`a\u0003\f\u0006\u0000"+
		"ab\u0005\u001b\u0000\u0000bc\u0005\u0016\u0000\u0000cd\u0005\u0003\u0000"+
		"\u0000d\u0011\u0001\u0000\u0000\u0000\u0005\u001817PX";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}