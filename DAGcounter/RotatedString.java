import java.util.Comparator;

public class RotatedString implements Comparable<RotatedString>
{
    private String string;
    private static int start = 0;
    
    public RotatedString(String string)
    {
        this.string = string;
    }
    
    public static void rotate()
    {
        start++;
    }
    
    public static void resetRotation()
    {
        start = 0;
    }
    
    public String toString()
    {
        return string.substring(start) + string.substring(0, start);
    }
    
    public String originalString()
    {
        return string;
    }
    
    public char charAt(int i)
    {
        return string.charAt((start + i) % length());
    }
    
    public int length()
    {
        return string.length();
    }
    
    public boolean startsWith(RotatedString that)
    {
        return this.string.startsWith(that.string);
    }
    
    public int compareTo(RotatedString that)
    {
        int N = Math.min(this.length(), that.length());
        
        for (int i = 0; i < N; i++)
        {
            if (this.charAt(i) < that.charAt(i))
                return -1;
            if (this.charAt(i) > that.charAt(i))
                return +1;
        }
        
        return this.length() - that.length();
    }
    
    public static Comparator<RotatedString> separatedSizeOrder()
    {
        return new SeparatedSizeOrder();
    }
    
    private static class SeparatedSizeOrder implements Comparator<RotatedString>
    {
        public int compare(RotatedString s, RotatedString t)
        {
            if (s.length() != t.length())
                return s.length() - t.length();
            else
                return s.compareTo(t);
        }
    }
}