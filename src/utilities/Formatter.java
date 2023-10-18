package utilities;

public class Formatter {
    public String formatter(String content, String title) {
        StringBuilder sb = new StringBuilder();
        int frameWidth = 100;
        String frameGlyph = "*";
        String border = frameGlyph + frameGlyph.repeat(frameWidth - 2) + frameGlyph + "\n";
        String[] lines = content.split("\n");

        sb.append(border);

        // Print the title centered under the top border
        int titlePadding = (frameWidth - 2 - title.length()) / 2;
        String formattedTitle = frameGlyph + " ".repeat(titlePadding) + title + " ".repeat(titlePadding) + frameGlyph + "\n";
        sb.append(formattedTitle);

        sb.append(border);

        for (String line : lines) {
            int leftPadding = 42;
            int rightPadding = frameWidth - 2 - line.length() - leftPadding;
            String formattedLine = frameGlyph + " ".repeat(leftPadding) + line + " ".repeat(rightPadding) + frameGlyph + "\n";
            sb.append(formattedLine);
        }

        sb.append(border);
        return sb.toString();
    }
}