package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter



public class PokemonPojo {
    private int count;
    private String next;
    private String previous;
    private List<PokemonCharacterPojo> results;
}
