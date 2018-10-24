{ pkgs ?  import <nixpkgs> {} }:
pkgs.mkShell {

  # needed pkgs
  # -----------
  buildInputs = with pkgs; [
    sbt
    (jetbrains.idea-ultimate.overrideAttrs( old: rec {
        version = "2018.2.5";
        name = "idea-ultimate-${version}";
        src = fetchurl {
          url = "https://download.jetbrains.com/idea/ideaIU-${version}-no-jdk.tar.gz";
          sha256 = "105mzbqm3bx05bmkwyfykz76bzgzzgb9hb6wcagb9fv7dvqyggg6";
        };
      })
    )
    git
  ];

  # run this on start
  # -----------------
  shellHook = ''
    export HELLO="world"
  '';
}
