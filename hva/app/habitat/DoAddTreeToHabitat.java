package hva.app.habitat;

import hva.app.exception.UnknownTreeKeyException;
import hva.core.Estacao;
import hva.core.Habitat;
import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import hva.core.Tree;
import hva.core.exception.DuplicateTreeIdException;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownTreeIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String idH = Form.requestString(Prompt.habitatKey());
    String idT = Form.requestString(Prompt.treeKey());
    String name = Form.requestString(Prompt.treeName());
    int age = Form.requestInteger(Prompt.treeAge());
    int difficulty = Form.requestInteger(Prompt.treeDifficulty());
    String type = Form.requestString(Prompt.treeType());
    while (!(type.equals("PERENE") | type.equals("CADUCA"))) {
      type = Form.requestString(Prompt.treeType());
    }
    if (_receiver.checkIdTreeExists(idT)) {
      throw new DuplicateTreeKeyException(idT);
    }
    try {
      _receiver.createTree(idT, name, type, age, difficulty);
      Habitat h = _receiver.getHabitatById(idH);
      Tree t = _receiver.getTreeById(idT);
      Estacao e = _receiver.get_estacao();
      h.addTree(t);
      _display.addLine("ÁRVORE" + "|" + idT + "|" + name + "|" + age + "|" + difficulty + "|" + type +
              "|" + t.ciclo_biologico(e));
    }
    catch (UnknownHabitatIdException uhie){
      throw new UnknownHabitatKeyException(uhie.getKey());
    }
    catch (UnknownTreeIdException utie){
      throw new UnknownTreeKeyException(utie.getKey());
    }
    catch (DuplicateTreeIdException dtie){
      throw new DuplicateTreeKeyException(dtie.getKey());
    }

    _display.display();
  }
}

