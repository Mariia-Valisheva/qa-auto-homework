package utils;

public enum LanguagesForSteppik {
    Бел("Шукаць"),
    De("Suche"),
    Es("Buscar"),
    Po("Procurar"),
    Русс("Искать"),
    Укр("Шукати"),
    En("Search");

    public final String description;
    LanguagesForSteppik(String description) {
        this.description = description;
    }
}
