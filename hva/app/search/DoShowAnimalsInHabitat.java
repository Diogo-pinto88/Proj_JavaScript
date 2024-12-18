package hva.app.search;

import hva.app.habitat.Prompt;
import hva.core.Animal;
import hva.core.Habitat;
import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.exception.UnknownHabitatIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;
//FIXME add more imports if needed

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    //FIXME add command fields
  }

  @Override
  protected void execute() throws CommandException {
    String id = Form.requestString(Prompt.habitatKey());
    try {
      Habitat h = _receiver.getHabitatById(id);
      for(Animal a : h.get_animais()){
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
    catch (UnknownHabitatIdException e){
      //impossible to happen
    }
  }
}
