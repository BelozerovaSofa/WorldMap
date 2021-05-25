package by.pomidor.domain;

public class Country
{
    private String name;
    private String capital;
    private String flagImagePath;
    private String description;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCapital()
    {
        return capital;
    }

    public void setCapital(String capital)
    {
        this.capital = capital;
    }

    public String getFlagImagePath()
    {
        return flagImagePath;
    }

    public void setFlagImagePath(String flagImagePath)
    {
        this.flagImagePath = flagImagePath;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "Country{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", flagImagePath='" + flagImagePath + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
