package hva.core;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.exception.*;

import java.io.*;
import java.util.*;

import static java.lang.Math.log;
import static java.lang.Math.max;

/**
 * This class is responsible for the overall management and organization of entities
 */
public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;

  private final HotelManager _manager;
  private final List<Animal> _animais;
  private final List<Employee> _funcionarios;
  private final List<Habitat> _habitats;
  private final List<Specie> _especies;
  private Estacao _estacao;
  private final List<Vaccine> _vacinas;
  private final List<Tree> _arvores;
  private final List<Register> _registos;

  private boolean _changed;

  public Hotel(HotelManager h) {
    this._changed = false;
    this._manager = h;
    this._animais = new ArrayList<>();
    this._funcionarios = new ArrayList<>();
    this._habitats = new ArrayList<>();
    this._especies = new ArrayList<>();
    this._estacao = Estacao.PRIMAVERA;
    this._vacinas = new ArrayList<>();
    this._arvores = new ArrayList<>();
    this._registos = new ArrayList<>();
  }

  /**
   * Check's if there has been any change in the state.
   *
   * @return true if the state has changed, false otherwise.
   **/
  public boolean HasChanged() {
    return _changed;
  }

  /**
   * Modify's the changed state.
   **/
  public void SetAsChanged() {
    _changed = true;
  }

  public void SetAsSaved() {
    _changed = false;
  }


  public List<Animal> get_animais() {
    _animais.sort(Comparator.comparing(a -> a.get_id().toLowerCase()));
    return _animais;
  }

  public HotelManager getManager() {
    return _manager;
  }

  public List<Employee> get_funcionarios() {
    _funcionarios.sort(Comparator.comparing(f -> f.get_id().toLowerCase()));
    return Collections.unmodifiableList(_funcionarios);
  }

  public List<Habitat> get_habitats() {
    _habitats.sort(Comparator.comparing(h -> h.get_id().toLowerCase()));
    return _habitats;
  }

  public List<Tree> get_trees() {
    _arvores.sort(Comparator.comparing(t -> t.get_id().toLowerCase()));
    return _arvores;
  }

  public Estacao get_estacao() {
    return _estacao;
  }

  public List<Vaccine> get_vacinas() {
    _vacinas.sort(Comparator.comparing(v -> v.get_id().toLowerCase()));
    return _vacinas;
  }

  public List<Register> get_registos() {
    return Collections.unmodifiableList(_registos);
  }

  public List<Register> get_registos(Vaccine v) {
    List<Register> l = new ArrayList<>();
    String id = v.get_id();
    for (Register r: _registos)
      if (r.get_vacina().get_id().equals(id))
        l.add(r);
    return Collections.unmodifiableList(l);
  }

  public List<Register> get_registos(Animal a) {
    List<Register> l = new ArrayList<>();
    String id = a.get_id();
    for (Register r: _registos)
      if (r.get_animal().get_id().equals(id))
        l.add(r);
    return Collections.unmodifiableList(l);
  }

  /**
   * Register an Animal and save it in the list.
   *
   * @param id        Animal´s id.
   * @param name      Animal´s name.
   * @param habitatId Habitat´s id.
   * @param speciesId Specie´s id.
   * @throws DuplicateAnimalIdException if the id already exists.
   * @throws UnknownHabitatIdException  if the habitat that we are using to store the animal doesn't exists.
   * @throws UnknownSpeciesIdException  if the specie that we are using to store the animal doesn't exists.
   **/
  public void registerAnimal(String id, String name, String habitatId, String speciesId)
          throws DuplicateAnimalIdException, UnknownHabitatIdException, UnknownSpeciesIdException {
    if (!checkIdAnimalExists(id)) {
      Habitat h = getHabitatById(habitatId);
      Specie s = getSpecieById(speciesId);
      Animal a = new Animal(id, name, h, s);
      _animais.add(a);
      s.addAnimal(a);
      h.addAnimal(a);
    }
    else {
      throw new DuplicateAnimalIdException(id);
    }
  }

  /**
   * Register a Specie and save it in the list.
   *
   * @param id   Specie's id.
   * @param name Specie´s name.
   * @throws DuplicateSpecieIdException if the specie already exists.
   */
  public void registerSpecies(String id, String name) throws DuplicateSpecieIdException {
    if (!checkIdSpecieExists(id)) {
      Specie s = new Specie(id, name);
      _especies.add(s);
    } else {
      throw new DuplicateSpecieIdException(id);
    }
  }

  /**
   * Register an Employee and save them in the list.
   *
   * @param id      Employee's id
   * @param name    Employee's name
   * @param empType Employee's type
   * @throws DuplicateEmployeeIdException if the Employee already exists.
   */
  public void registerEmployee(String id, String name, String empType) throws DuplicateEmployeeIdException {
    if (!checkIdEmployeeExists(id)) {
      if (empType.equals("VET")) {
        Veterinarian v = new Veterinarian(id, name);
        _funcionarios.add(v);
      } else if (empType.equals("TRT")) {
        Zookeeper z = new Zookeeper(id, name);
        _funcionarios.add(z);
      }
    }
    else {
      throw new DuplicateEmployeeIdException(id);
    }
  }

  /**
   * Register a Vaccine and save it in the list.
   *
   * @param id         Vaccine´s id
   * @param name       Vaccine´s name
   * @param speciesIds Specie´s id
   * @throws UnknownSpeciesIdException   if the Specie doesn´t exist
   * @throws DuplicateVaccineIdException if the Specie already exists
   */
  public void registerVaccine(String id, String name, String[] speciesIds)
          throws UnknownSpeciesIdException, DuplicateVaccineIdException {
    if (!checkIdVaccineExists(id)) {
      List<Specie> e = new ArrayList<>();
      for (String s : speciesIds) {
        if (!checkIdSpecieExists(s))
          throw new UnknownSpeciesIdException(s);
        else {
          e.add(getSpecieById(s));
        }
      }
      Vaccine v = new Vaccine(id, name, e);
      _vacinas.add(v);
    } else {
      throw new DuplicateVaccineIdException(id);
    }
  }

  /**
   * Register a Habitat and save it in the list.
   *
   * @param id   Habitat's id.
   * @param name Habitat's name.
   * @param area The area of the habitat (e.g., in square meters).
   * @return The created Habitat object.
   * @throws DuplicateHabitatIdException if the habitat id already exists.
   **/
  public Habitat registerHabitat(String id, String name, int area) throws DuplicateHabitatIdException {
    if (!checkIdHabitatExists(id)) {
      Habitat h = new Habitat(id, name, area);
      _habitats.add(h);
      return h;
    }
    throw new DuplicateHabitatIdException(id);
  }

  /**
   * Add a responsibility to an employee based on their ID.
   *
   * @param id             Employee's id.
   * @param responsibility The responsibility to be added.
   * @throws UnknownEmployeeIdException if the employee id does not exist.
   *
   **/
  public void addResponsibility(String id, String responsibility) throws UnknownEmployeeIdException{
    Employee e = getEmployeeById(id);
    e.addResponsability(responsibility);
  }

  /**
   * This method removes a specific responsibility from an employee.
   * If the employee is found by their ID, the responsibility is removed from their list of responsibilities.
   *
   * @param id The ID of the employee whose responsibility is being removed.
   * @param responsibility The specific responsibility to be removed from the employee.
   * @throws UnknownEmployeeIdException If the employee with the given ID is not found.
   */
  public void removeResponsibility(String id, String responsibility) throws UnknownEmployeeIdException{
    Employee e = getEmployeeById(id);
    e.removeResponsability(responsibility);
  }

  /**
   * Create a Tree and save it in the list.
   *
   * @param id   Tree's id.
   * @param name Tree's name.
   * @param tipo Type of tree ("CADUCA" for deciduous or any other value for evergreen).
   * @param age  Age of the tree.
   * @param diff A specific attribute (e.g., difficulty level or other tree characteristic).
   * @throws DuplicateTreeIdException if the tree id already exists.
   **/
  public void createTree(String id, String name, String tipo, int age, int diff) throws DuplicateTreeIdException {
    if (!checkIdTreeExists(id)) {
      if (tipo.equals("CADUCA")) {
        Caduca c = new Caduca(id, name, age, diff);
        _arvores.add(c);
      } else {
        Perene p = new Perene(id, name, age, diff);
        _arvores.add(p);
      }
    } else {
      throw new DuplicateTreeIdException(id);
    }
  }

  /**
   * Check if an animal with the given ID already exists in the list.
   *
   * @param id Animal's id to check.
   * @return true if an animal with the given ID exists, false otherwise.
   **/
  public boolean checkIdAnimalExists(String id) {
    for (Animal a : _animais) {
      if (a.get_id().equals(id))
        return true;
    }
    return false;
  }

  /**
   * Check if a habitat with the given ID already exists in the list.
   *
   * @param id Habitat's id to check.
   * @return true if a habitat with the given ID exists, false otherwise.
   **/
  public boolean checkIdHabitatExists(String id) {
    for (Habitat h : _habitats) {
      if (h.get_id().equals(id))
        return true;
    }
    return false;
  }

  /**
   * Check if an employee with the given ID already exists in the list.
   *
   * @param id Employee's id to check.
   * @return true if an employee with the given ID exists, false otherwise.
   **/
  public boolean checkIdEmployeeExists(String id) {
    for (Employee e : _funcionarios) {
      if (e.get_id().equals(id))
        return true;
    }
    return false;
  }

  /**
   * This method checks if a given employee is a veterinarian and exists in the system.
   * It compares the employee's ID and type to ensure the employee is a veterinarian.
   *
   * @param e The employee to check.
   * @return true if the employee exists in the system and is a veterinarian, false otherwise.
   */
  public boolean checkIdVetExists(Employee e){
    for (Employee em : _funcionarios){
      if(em.get_id().equals(e.get_id()) && e.get_tipo().equals("VET")){
        return true;
      }
    }
    return false;
  }

  /**
   * Check if a tree with the given ID already exists in the list.
   *
   * @param id Tree's id to check.
   * @return true if a tree with the given ID exists, false otherwise.
   **/
  public boolean checkIdTreeExists(String id) {
    for (Tree t : _arvores) {
      if (t.get_id().equals(id))
        return true;
    }
    return false;
  }

  /**
   * Check if a vaccine with the given ID already exists in the list.
   *
   * @param id Vaccine's id to check.
   * @return true if a vaccine with the given ID exists, false otherwise.
   **/
  public boolean checkIdVaccineExists(String id) {
    String id1 = id.toUpperCase();
    for (Vaccine v : _vacinas) {
      if (v.get_id().equals(id1))
        return true;
    }
    return false;
  }

  /**
   * Check if a species with the given ID already exists in the list.
   *
   * @param id Species' id to check.
   * @return true if a species with the given ID exists, false otherwise.
   **/
  public boolean checkIdSpecieExists(String id) {
    for (Specie s : _especies) {
      if (s.get_id().equals(id))
        return true;
    }
    return false;
  }

  /**
   * This method checks if a given responsibility exists in the system.
   * The responsibility can be identified by its ID, which could either match a habitat ID or a species ID.
   *
   * @param id The ID of the responsibility to check (could be a habitat or species).
   * @return true if the responsibility exists, false otherwise.
   */
  public boolean checkIfResponsibilityExists(String id) {
    for (Habitat h : _habitats) {
      if (h.get_id().equals(id))
        return true;
    }
    for(Specie s : _especies){
      if(s.get_id().equals(id))
        return true;
    }
    return false;
  }

  /**
   * Get a species by its ID.
   *
   * @param id The species' id to search for.
   * @return The Specie object with the matching ID.
   * @throws UnknownSpeciesIdException if no species with the given ID is found.
   **/
  public Specie getSpecieById(String id) throws UnknownSpeciesIdException {
    for (Specie s : _especies) {
      if (s.get_id().equals(id))
        return s;
    }
    throw new UnknownSpeciesIdException(id);
  }

  /**
   * Get a habitat by its ID.
   *
   * @param id The habitat's id to search for.
   * @return The Habitat object with the matching ID.
   * @throws UnknownHabitatIdException if no habitat with the given ID is found.
   **/
  public Habitat getHabitatById(String id) throws UnknownHabitatIdException {
    for (Habitat h : _habitats) {
      if (h.get_id().equals(id))
        return h;
    }
    throw new UnknownHabitatIdException(id);
  }

  /**
   * Get a employee by their ID.
   *
   * @param id The employee's id to search for.
   * @return The Employee object with the matching ID.
   * @throws UnknownEmployeeIdException if no employee with the given ID is found.
   **/
  public Employee getEmployeeById(String id) throws UnknownEmployeeIdException {
    for (Employee e : _funcionarios) {
      if (e.get_id().equals(id)) {
        return e;
      }
    }
    throw new UnknownEmployeeIdException(id);
  }

  /**
   * Get a tree by its ID.
   *
   * @param id The tree's id to search for.
   * @return The Tree object with the matching ID.
   * @throws UnknownTreeIdException if no tree with the given ID is found.
   **/
  public Tree getTreeById(String id) throws UnknownTreeIdException {
    for (Tree t : _arvores) {
      if (t.get_id().equals(id)) {
        return t;
      }
    }
    throw new UnknownTreeIdException(id);
  }

  /**
   * Get a Animal by its ID.
   *
   * @param id The Animal's id to search for.
   * @return The Animal object with the matching ID.
   * @throws UnknownAnimalIdException if no Animal with the given ID is found.
   **/
  public Animal getAnimalById(String id) throws UnknownAnimalIdException {
    for (Animal a : _animais) {
      if (a.get_id().equals(id)) {
        return a;
      }
    }
    throw new UnknownAnimalIdException(id);
  }

  /**
   * This method retrieves a vaccine by its unique ID.
   *
   * @param id The ID of the vaccine to search for.
   * @return The Vaccine object that matches the given ID.
   * @throws UnknownVaccineIdException If no vaccine with the given ID is found.
   */
  public Vaccine getVaccineById(String id) throws UnknownVaccineIdException{
    for(Vaccine v : _vacinas){
      if(v.get_id().equals(id)){
        return v;
      }
    }
    throw new UnknownVaccineIdException(id);
  }

  /**
   * This method calculates the number of zookeepers responsible for a specific habitat.
   *
   * @param h The habitat for which we are calculating the number of responsible zookeepers.
   * @return The number of zookeepers responsible for the given habitat.
   */
  public int numero_de_tratadores_do_habitat(Habitat h){
    int numero_de_tratadores = 0;
    List<Employee> lista = get_funcionarios();
    for(Employee e : lista){
      for(String s : e.getResponsabilidadesById()){
        if(h.get_id().equals(s)){
          numero_de_tratadores++;
        }
      }
    }
    return numero_de_tratadores;
  }

  /**
   * This method calculates the number of veterinarians responsible for a specific species.
   *
   * @param e The species for which we are calculating the number of responsible veterinarians.
   * @return The number of veterinarians responsible for the given species.
   */
  public int n_veterinarios(Specie e){
    int n_veterinarios = 0;
    List<Employee> lista = get_funcionarios();
    for(Employee employee : lista){
      for(String s : employee.getResponsabilidadesById()){
        if(e.get_id().equals(s)){
          n_veterinarios++;
        }
      }
    }
    return n_veterinarios;
  }



  /**
   * This method calculates the satisfaction level of a veterinarian based on the species they are responsible for.
   *
   * @param e The veterinarian employee whose satisfaction is being calculated.
   * @return The satisfaction level as an integer value.
   */
  public int getSatisfacaoVet(Employee e){
    int val = 0;
    try{
      for (String si : e.getResponsabilidadesById()) {
        Specie s = getSpecieById(si);
        int val1 = s.get_animaisPertencentes().size() / n_veterinarios(s);
        val += val1;
      }
      return 20 - val;
    }
    catch(UnknownSpeciesIdException usie){
      //impossible to happen
    }
    return 0;
  }

  /**
   * This method calculates the satisfaction level of a zookeeper based on the habitats they manage.
   *
   * @param e The zookeeper whose satisfaction is being calculated.
   * @return The satisfaction level as an integer value.
   */
  public int getSatisfacaoTrt (Employee e){
    double valor = 0.00;
    try {
      for (String s : e.getResponsabilidadesById()) {
        Habitat h = getHabitatById(s);
        valor += (trabalho_no_habitat(h) / numero_de_tratadores_do_habitat(h));
      }
      return ((int) (300 - valor));
    }
    catch(UnknownHabitatIdException uhie){
      // impossible to happen
    }
    return 0;
  }

  /**
   * This method calculates the cleaning effort required for a specific tree based on its difficulty,
   * seasonal effort, and age.
   *
   * @param t The tree for which the cleaning effort is being calculated.
   * @return The cleaning effort as a double value.
   */
  public double esforco_limpeza(Tree t){
    double valor  = 0.00;
    valor = t.get_dificuldadeLimpeza() * t.esforco_sazonal(_estacao) * log(t.get_idade() + 1);
    return valor;
  }

  /**
   * This method counts the number of animals in a habitat that belong to the same species as the given animal,
   * excluding the animal itself.
   *
   * @param a The animal for which we are finding other animals of the same species.
   * @param h The habitat in which we are looking for animals of the same species.
   * @return The count of animals in the habitat that belong to the same species as the animal a, excluding a itself.
   */
    public int especieIgual(Animal a, Habitat h){
      Specie s = a.get_specie();
      int cnt = 0;
      for (Specie sp : h.getSpecies()) {
        if (sp.equals(s)) {
          cnt++;
        }
      }
      return cnt - 1;
    }

    /**
     * This method calculates the number of animals in the habitat that belong to a different species than the given animal.
     *
     * @param a The animal for which we are calculating the number of different species.
     * @param h The habitat in which we are counting animals of different species.
     * @return The number of animals in the habitat h that belong to a different species than the animal a.
     */
    public int especieDiferente (Animal a, Habitat h){
      int valor = h.get_animais().size() - (especieIgual(a, h) + 1);
      return valor;
    }

  /**
   * This method calculates the total amount of work required to maintain a habitat.
   * The calculation includes the cleaning effort for trees, the habitat's area, and the number of animals in the habitat.
   *
   * @param h The habitat for which the total work effort is being calculated.
   * @return The total work effort required for the habitat as a double value.
   */
    public double trabalho_no_habitat (Habitat h){
      double valor = 0.00;
      for (Tree t : h.get_arvores()) {
        valor += esforco_limpeza(t);
      }
      valor += h.get_area() + (3 * h.get_animais().size());
      return valor;
    }

  /**
   * This method retrieves the corresponding Influence type based on the input string.
   *
   * @param s A string representing the type of influence.
   * @return The corresponding Influence enum.
   */
    public Influence getInfluence(String s){
      if(s.equals("POS"))
        return Influence.POSITIVA;
      if(s.equals("NEG"))
        return Influence.NEGATIVA;
      else
        return Influence.NEUTRA;
    }

  /**
   * This method converts an Influence enum value into its corresponding integer value.
   *
   * @param e The Influence enum (NEGATIVA, NEUTRA, or POSITIVA).
   * @return The integer value corresponding to the given Influence:
   *         - NEGATIVA returns -20,
   *         - NEUTRA returns 0,
   *         - POSITIVA returns 20.
   */
    public int InfluenceToValue(Influence e){
        return switch (e) {
            case NEGATIVA -> -20;
            case NEUTRA -> 0;
            case POSITIVA -> 20;
        };
    }

  /**
   * This method calculates the potential damage a vaccine can cause to an animal,
   * based on the species mismatch between the animal and the species that the vaccine is designed for.
   * The damage is calculated by comparing the name length and common characters between species names.
   *
   * @param v The vaccine that is being checked.
   * @param a The animal to which the vaccine is applied.
   * @return The maximum damage value calculated based on species comparison.
   */
    public int calculaDano(Vaccine v, Animal a){
      int valor = 0;
      List<Integer> lista = new ArrayList<>();
      for(Specie e : v.get_especies()){
        if(e.equals(a.get_specie()))
          return 0;
        else {
          valor = tamanhoNomes(a.get_specie(), e) - carateresComuns(a.get_specie(), e);
          lista.add(valor);
        }
      }
      return Collections.max(lista);
    }

  /**
   * This method converts the damage value from the vaccine to a descriptive string representing
   * the outcome of administering the vaccine to the animal.
   *
   * @param v The vaccine that is being checked.
   * @param a The animal to which the vaccine is applied.
   * @return A string representing the outcome.
   */
    public String converteDano(Vaccine v, Animal a){
      int dano = calculaDano(v, a);
      for(Specie s : v.get_especies()) {
        if (s.equals(a.get_specie()) && dano == 0)
          return "NORMAL";
      }
      if(dano == 0)
        return "CONFUSÃO";
      if(dano >= 1 && dano <= 4)
        return "ACIDENTE";
      else
        return "ERRO";
    }

  /**
   * This method records the administration of a vaccine to an animal by a veterinarian.
   * It updates the health status of the animal based on the damage caused by the vaccine.
   *
   * @param vet The veterinarian administering the vaccine.
   * @param a The animal receiving the vaccine.
   * @param vac The vaccine being administered.
   */
    public void adicionaVacina(Veterinarian vet, Animal a, Vaccine vac){
      _registos.add(new Register(vet, a, vac));
      a.atualizaEstadoSaude(converteDano(vac, a));
    }

  /**
   * This method returns the length of the longest name between two species.
   *
   * @param s1 The first species.
   * @param s2 The second species.
   * @return The length of the longer name between the two species.
   */
    public int tamanhoNomes(Specie s1, Specie s2){
      return max(s1.get_name().length(), s2.get_name().length());
    }

  /**
   * This method calculates the number of common characters between the names of two species.
   * It converts the species names into sets of characters and finds the intersection of the two sets.
   *
   * @param s1 The first species.
   * @param s2 The second species.
   * @return The number of characters that appear in both species' names.
   */
  public int carateresComuns(Specie s1, Specie s2) {
    Set<Character> conjunto1 = new HashSet<>();
    for (char c : s1.get_name().toCharArray()) {
      conjunto1.add(c);
    }
    int cnt = 0;
    for (char c : s2.get_name().toCharArray()) {
      if (conjunto1.contains(c))
        cnt++;
    }
    return cnt;
  }

  /**
   * This method advances the current season to the next one in the cycle.
   * The seasons follow a predefined order: PRIMAVERA -> VERÃO -> OUTONO -> INVERNO.
   * After INVERNO, the season resets to PRIMAVERA.
   *
   * @return The integer value representing the new season.
   */
    public int advanceSeason(){
      int i = seasonToInt(_estacao);
      if(i <= 2 && i >= 0){
        this._estacao = IntToSeason(i+1);
        return i+1;
      }
      else{
        this._estacao = Estacao.PRIMAVERA;
        return 0;
      }
    }

  /**
   * This method converts an integer into the corresponding season.
   *
   * @param i The integer representing a season.
   * @return The corresponding Estacao enum, or null if the integer is out of range.
   */
    public Estacao IntToSeason(int i){
        return switch (i) {
            case 0 -> Estacao.PRIMAVERA;
            case 1 -> Estacao.VERAO;
            case 2 -> Estacao.OUTONO;
            case 3 -> Estacao.INVERNO;
            default -> null;
        };
    }

  /**
   * This method converts a season into the corresponding integer value.
   *
   * @param e The Estacao enum representing the season.
   * @return The integer representing the season.
   */
    public int seasonToInt(Estacao e){
      if(e.getNome().equals("Primavera"))
        return 0;
      if(e.getNome().equals("Verão"))
        return 1;
      if(e.getNome().equals("Outono"))
        return 2;
      else
        return 3;
    }

    /**
     * This method calculates the satisfaction level of an animal.
     * The formula for satisfaction includes factors such as the presence of animals of the same and different species, and the habitat area.
     *
     * @param a The Animal for which the satisfaction is being calculated.
     * @return The satisfaction level of the animal as an integer value.
     */

     public int getSatisfacaoAnimal(Animal a) {
     int valor = 0;
     if(checkIdAnimalExists(a.get_id())) {
       valor = (20 + (3 * especieIgual(a, a.get_habitat())) - (2 * especieDiferente(a, a.get_habitat())) +
               (a.get_habitat().get_area() / a.get_habitat().get_animais().size()) +
               InfluenceToValue(a.get_habitat().getInfluencia(a.get_specie())));
     }
     return valor;
     }
  }

