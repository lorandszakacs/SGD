package com.lorandszakacs.sg

import com.lorandszakacs.sg.model.ModelName

/**
  *
  * @author Lorand Szakacs, lorand.szakacs@busymachines, lsz@lorandszakacs.com
  * @since 12 Jul 2016
  *
  */
object Favorites {

  private lazy val _modelsNames =
    List(
      "aeterna", "agatha", "ailiqueen", "aisline", "ajilee", "aleli", "alerose", "alerosebunny", "alicesey", "alicesudos",
      "alle", "almendra", "amarna", "amity_", "anabuhr", "ancalagon", "aniston", "annalee", "annasthesia", "antaje",
      "antigone", "arabella", "arielscout", "arwen", "asami", "astraia", "atomic", "attycus", "aubrey", "aurorah",
      "avrora", "aymi", "bae", "bambu", "bandit", "baxter_", "bea", "beckah", "blair", "bluedette",
      "bob", "boobafettish", "brewin", "bruna", "brunabruce", "brynx", "bully", "cadorna", "calalini", "candyhell",
      "carrina", "cartoon", "casanova", "cecylia", "ceriz", "chad", "charm", "chelss", "cheri", "cherrybuttonz",
      "chrisrayn", "circa", "clareon", "cleoo", "coolicio", "coralinne", "cyberre", "dali", "damsel", "damselfly",
      "dawud", "delacour", "delora", "demonia", "devilins", "dimarti", "dimples", "discordia", "dollyd", "dwam",
      "ecco", "eden", "effy", "einiss", "eleena", "elisa", "elisarios", "elisarose", "ellie", "elliemouse",
      "elliott", "ellqvist", "emmalou", "emmameow", "episkey", "equateur", "evette", "exning", "fay", "fennek",
      "feryn", "fishball", "flux", "fridah", "frosty", "gaarnet", "gallows", "gatete", "glitch", "gogo",
      "gypsyy", "haven", "heathen", "helenahopper", "herem", "hexe", "hildegarde", "hind", "hylia", "idony",
      "idunn", "illusion", "indi", "ivory", "ivylina", "jackye", "jam", "jamity", "janedoe", "janesinner",
      "janette", "jessicalou", "joker", "july", "kacy", "kailah", "kala", "kaotika", "karanlit", "katherine",
      "kaylindear", "kemper", "kirbee", "kush", "ladyviolet", "lait", "lalka", "lanei", "lass", "lavonne",
      "leella", "lego", "lexiexj", "lillianrose", "lilymai", "lilyt", "liryc", "liu", "livay", "liya",
      "lolitta", "lollimila", "lovelace", "luana", "lucerne", "lucid", "lumo", "lunar", "luxlee", "lylie",
      "lyuba", "lyvia", "lyxzen", "machete", "magpie", "mah", "malicatsz", "manko", "maokiz", "marajade",
      "margarette", "marialauriejupiter", "marlene", "marquise", "mars", "maud", "mel", "melbee", "mendacia", "mermaid",
      "mialeveret", "miele", "milenci", "mille", "milloux", "missfernandez", "mistica", "mistyy", "mnemozyne", "moon",
      "morrigan", "narciss", "natashalegeyda", "nattybohh", "nebula", "nefka", "nemesis", "nery", "ness", "noel",
      "nubia", "octaviamay", "okami", "ondinae", "oogie", "orion", "paloma", "pandie", "pandub", "patton",
      "paulam", "pekopeko", "pesky", "phoenix", "pia", "piedpiper", "pilot", "pirate", "plum", "polaw",
      "poox", "porcelinna", "porphyria", "posh", "priscila", "prism", "prussia", "psyco_", "pulp", "pyke",
      "queenblossom", "rachelle", "radeo", "raleigh", "rambo", "raphaelite", "rawruh", "rebyt", "reed", "revenge",
      "rhue", "riae", "ripley", "riversong", "rjen", "rouge", "rourke", "sabbbre", "saint", "salliss",
      "saralilith", "saria", "sashabrink", "satelina", "satin", "satinkaa", "saturn", "savanna", "scribbles", "sedona",
      "serenna", "shamandalie", "shannon_", "shaunanyx", "shine", "shotgun", "sibila", "silencia", "silvi", "sinnah",
      "sinni", "skinbyrd", "skydot", "skyhook", "slim", "smash", "spliff", "spock", "stephy", "sterling_",
      "stormyent", "sua", "sugarkane90", "sundew", "sunny", "tabbytha", "talena", "tangerine", "tanyabat", "tarion",
      "tasha", "tchip", "tegnwar", "terrorlydia", "terrox", "teslaa", "tigh", "titan_", "torasuicide", "totiga",
      "trece", "tristyn", "twiitch", "ultima", "ultramarie", "undead", "vaniislima", "vanp", "vareon", "vassilis",
      "vaughn", "vehera", "veive", "velour", "venom", "verde", "vice", "viking", "vikki", "vivid",
      "waikiki", "whiskey", "wilhelmine", "wolf", "yesenia", "yugen", "yupa", "yusuracircus", "yuxi", "zad",
      "zeezee", "zell", "zenit", "zephi", "zilvia", "zippo", "zoli"
    ).sorted.distinct

  private val _new = List[String](
  ).filterNot(_.isEmpty).sorted.distinct

  val modelNames: List[ModelName] = (if (_new.isEmpty) _modelsNames else _new) map ModelName.apply

  def codeFriendlyDisplay: String = (_modelsNames ++ _new)
    .map(ModelName.apply).map(_.name).distinct.sorted.grouped(10)
    .map(_.mkString("\"", "\", \"", "\"")).mkString("List(\n", ",\n", "\n)")
}
