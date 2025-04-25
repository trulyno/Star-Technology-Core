{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
  };

  outputs = {
    self,
    nixpkgs,
    ...
  }: 
  let
    system = "x86_64-linux";
    pkgs = nixpkgs.legacyPackages.${system};
  in {    
    # Development shell environment
    devShells.${system} = {
      default = pkgs.mkShell {
        name = "star-technology-core-dev";

        shellHook = ''
          export LD_LIBRARY_PATH="${pkgs.libglvnd}/lib"
        '';
      };
    };
  };
}
