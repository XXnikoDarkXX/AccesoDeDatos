#include <iostream>
#include <array>
#include <string>

using namespace std;

// Tipos (Apartado 0)
const int MAX = 130;

typedef struct{
    string  candidato;
    string  categoria;
    int     votos;
} TCandidato;

typedef array<TCandidato, MAX> TCandidatos;

typedef struct{
    TCandidatos candidatos;
    int         tam;
} TVotacion;

// Apartado 1 (Leer)
bool esIgual(const TCandidato &c, string candidato, string categoria){
    return c.candidato == candidato && c.categoria == categoria;
}
