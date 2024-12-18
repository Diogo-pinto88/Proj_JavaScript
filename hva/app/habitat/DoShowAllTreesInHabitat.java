package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.exception.UnknownHabitatIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.Habitat;
import  hva.core.Tree;


/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String id = Form.requestString(Prompt.habitatKey());
    try {
      Habitat h = _receiver.getHabitatById(id);
      for (Tree t : h.get_arvores()){
        _display.addLine("ÁRVORE|" + t.get_id() + "|" + t.get_nome () + "|" + (int) (t.get_idade() - t.get_idade()%1) + "|" +
                t.get_dificuldadeLimpeza() + "|" +  t.get_tipo() + "|" + t.ciclo_biologico(_receiver.get_estacao()));
      }
    }
    catch(UnknownHabitatIdException uhie){
      throw new UnknownHabitatKeyException(uhie.getKey());
    }
    _display.display();
  }
}
