package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.Tree;
import pt.tecnico.uilib.menus.Command;


/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }

  @Override
  protected void execute() {
    for (Habitat h: _receiver.get_habitats()) {
      _display.addLine("HABITAT|" + h.get_id() + "|" + h.get_nome() + "|" + h.get_area() + "|" + h.get_arvores().size());
      if(!h.get_arvores().isEmpty()){
        for(Tree t : h.get_arvores()){
          _display.addLine("ÁRVORE|" + t.get_id() + "|" + t.get_nome() + "|" + (int) (t.get_idade() - t.get_idade()%1) + "|" +
                  t.get_dificuldadeLimpeza() + "|" +  t.get_tipo() + "|" + t.ciclo_biologico(_receiver.get_estacao()));
        }
      }
    }
    _display.display();
  }
}
