using System;

namespace jogoDaVelha
{
    class Program
    {
        static int option = 0;
        static void Main()
        {
            Console.WriteLine("Seja bem-vindo ao Jogo de Pedra, Papel e Tesoura!");
            Console.WriteLine("\n[1] - Jogar\n[2] - Sair\n");

            while(option == 0)
            {
                Console.Write("Digite a opção desejada para continuar: ");
                option = Convert.ToInt32(Console.ReadLine());

                switch (option)
                {
                    case 1:
                        Game.start();
                        break;
                    case 2:
                        break;
                    default:
                        Console.WriteLine("Opção inválida. Tente novamente.");
                        option = 0;
                        break;
                }
            }
        }
    }

    class Game
    {
        static bool gameFinished = false;
        static int round = 0;
        static int userPoints;
        static int botPoints;
        static int userChoice;
        static int botChoice;
        static Random randomChoice = new Random();
        static string[] options = { "Pedra", "Papel", "Tesoura" };
        public static void start()
        {
            while(gameFinished == false)
            {
                Console.WriteLine(new string('-', 30));
                Console.WriteLine("RODADA ", round + 1);
                Console.WriteLine("[1] - Pedra\n[2] - Papel\n[3] - Tesoura\n\nDigite a sua escolha para esta rodada:");
                
                userChoice = Convert.ToInt32(Console.ReadLine());
                botChoice = Convert.ToInt32(randomChoice.Next(1, 4));

                if (userChoice == botChoice)
                {
                    Console.WriteLine($"EMPATE! Você escolheu a mesma opção que o seu adversário ({options[botChoice-1]})"); // INDEX OUT OF RANGE
                } else if(userChoice == 1 && botChoice == 3)
                {
                    Console.WriteLine($"VOCÊ GANHOU! Sua escolha foi {options[userChoice-1]} e a do seu adversário foi {options[botChoice-1]}");
                    userPoints += 1;
                }
                else if (userChoice == 2 && botChoice == 1)
                {
                    Console.WriteLine($"VOCÊ GANHOU! Sua escolha foi {options[userChoice-1]} e a do seu adversário foi {options[botChoice-1]}");
                    userPoints += 1;
                }
                else if (userChoice == 3 && botChoice == 2)
                {
                    Console.WriteLine($"VOCÊ GANHOU! Sua escolha foi {options[userChoice-1]} e a do seu adversário foi {options[botChoice-1]}");
                    userPoints += 1;
                } else
                {
                    Console.WriteLine($"VOCÊ PERDEU! Sua escolha foi {options[userChoice-1]} e a do seu adversário foi {options[botChoice-1]}");
                    botPoints += 1;
                }

                if(userPoints == 5)
                {
                    Console.WriteLine("FIM DE JOGO. VOCÊ FOI O VENCEDOR, PARABÉNS!");
                    Console.WriteLine("\nDigite qualquer tecla para voltar ao menu: ");
                    Console.ReadLine()
                    gameFinished=true;
                    //CONTINUAR AQUI

                } else if(botPoints == 5)
                {
                    Console.WriteLine("FIM DE JOGO. O ADVERSÁRIO FOI O VENCEDOR, BOA SORTE NA PRÓXIMA!");
                    Console.WriteLine("\nDigite qualquer tecla para voltar ao menu: ");
                }
            }
        }
    }
}