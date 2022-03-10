package DTO;

public class DFSTraversalDto {
    private String name , format;
    private long min , max;

    public String getName() {
        return name;
    }

    public void setKB(boolean input) {
        if(input)
        {
            Converter(1024);
        }

    }

    public void setMB(boolean input) {
        if(input)
        {
            Converter(1048576);
        }
    }

    public void setGB(boolean input) {
        if (input)
        {
            Converter(1073741824);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    private void Converter(long rate)
    {
        min *= rate;
        max *= rate;
    }

    private static long Converter(double rate , long value)
    {
        return (long) ( value * rate);
    }

    public static long GetKB(long value)
    {
        return Converter(0.0009765625,value);
    }
}
