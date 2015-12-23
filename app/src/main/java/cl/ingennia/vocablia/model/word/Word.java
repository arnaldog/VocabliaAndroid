package cl.ingennia.vocablia.model.word;

import android.annotation.SuppressLint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

@DatabaseTable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Word implements Serializable {
	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
	public int id;

	@DatabaseField
	public String name;

	@DatabaseField
	public String description;

	@DatabaseField
	public String example;

	@DatabaseField
	public String icon;

	@DatabaseField
	public String audio;

	@DatabaseField
	@JsonProperty("created_at")
//    @JsonSerialize(using = JsonDateSerializer.class)
	protected Date createdAt;

	@DatabaseField
	@JsonProperty("updated_at")
//    @JsonSerialize(using = JsonDateSerializer.class)
    protected Date updated_at;

	public Word() {
	}

	public boolean compareTo(Word word) {
		return (this.name == word.name);
	}

	public String toString() {
		ObjectMapper om = new ObjectMapper();
		String output = "";

		try {
			output = om.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return output;
//		String pattern = "{ id: %d, name: %s, description: %s, example: %s}";
//		return String.format(pattern, id, name, description, example);
	}

	public static Word deserialize(String json) throws
			JsonMappingException, IOException {
		return new ObjectMapper().readValue(json, Word.class);
	}

}
