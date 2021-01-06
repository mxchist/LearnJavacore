package exercise4;

import java.util.UUID;

public class Document {
	int pages;
	UUID name;

	Document(int pages) {
		this.pages = pages;
		this.name = UUID.randomUUID();
	}
}
