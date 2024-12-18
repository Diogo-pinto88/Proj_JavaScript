package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

import java.util.List;

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }
  
  @Override
  protected final void execute() {
    for (Animal a: _receiver.get_animais()){
      _display.add("ANIMAL|" + a.get_id() + "|" + a.get_name() + "|" + a.get_specie().get_id() + "|");
    List<String> l = a.get_estadoSaude();
    if(l.isEmpty())
      _display.add("VOID");
    else {
      boolean first = true;
      for (String s : l) {
        if (l.size() > 1 && !first)
          _display.add(",");
        _display.add(s);
        first = false;
      }
    }
    _display.add( "|" + a.get_habitat().get_id() + "\n");
    }
    _display.display();
  }
}
