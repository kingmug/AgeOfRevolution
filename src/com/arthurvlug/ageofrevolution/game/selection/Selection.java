package com.arthurvlug.ageofrevolution.game.selection;

import com.google.common.collect.Sets;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Selection {
     public static final Selection EMPTY = new Selection(Sets.newHashSet());

     private final Set<Selectable> selectables;
}
