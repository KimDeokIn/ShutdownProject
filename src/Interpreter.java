import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Map;
import java.util.TreeMap;

class Interpreter {
	private Map<String, String> st;
	private StreamTokenizer in;

	public Interpreter(StreamTokenizer in) throws IOException {
		this.in = in;
		st = new TreeMap<String, String>();
		in.commentChar('#');
		// #을 주석처리 문자로 쓰기 위한 처리
	}
	
	public String getWord(String id) throws IOException {
		if(!st.containsKey(id))
			throw new IOException();
		return st.get(id);
	}

	// Statement ::= Expression '=' Expression
	public void parseStatement() throws IOException {
		in.nextToken();
		String key = parseExpression();
		
		in.nextToken();
		if (in.ttype != '=')
			throw new IOException();
		
		in.nextToken();
		String value = parseExpression();
		
		st.put(key, value);
	}

	// Expression ::= Digit | String | #
	public String parseExpression() throws IOException {
		switch (in.ttype) {
		case StreamTokenizer.TT_NUMBER:
			return ""+(int)in.nval;
		case StreamTokenizer.TT_WORD:
			return in.sval;
		}
		return "#";
	}
}
