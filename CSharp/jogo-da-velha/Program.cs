using System;

namespace jogoDaVelha
{
    class Program
    {
        static void Main()
        {
            Game.menu();
        }
    }

    class Game
    {
        static int option = 0;
        static int round = 0;
        static bool gameFinished = false;
        static int userPoints;
        static int botPoints;
        static int userChoice;
        static int botChoice;
        static Random randomChoice = new Random();
        static string[] options = { "Pedra", "Papel", "Tesoura" };
        
        public static void menu()
        {
            option = 0;
            Console.WriteLine("Seja bem-vindo ao Jogo de Pedra, Papel e Tesoura!");
            Console.WriteLine("\n[1] - Jogar\n[2] - Sair\n");

            while (option == 0)
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
        
        public static void start()
        {
            gameFinished = false;
            round = 0;
            userPoints = 0;
            botPoints = 0;

            while (gameFinished == false)
            {
                round++;
                Console.WriteLine(new string('-', 30));
                Console.WriteLine($"RODADA {round}\n");
                Console.WriteLine("[1] - Pedra\n[2] - Papel\n[3] - Tesoura\n\nDigite a sua escolha para esta rodada:");
                
                userChoice = Convert.ToInt32(Console.ReadLine());
                botChoice = Convert.ToInt32(randomChoice.Next(1, 4));

                if (userChoice == botChoice)
                {
                    Console.WriteLine($"\nEMPATE! Você escolheu a mesma opção que o seu adversário ({options[botChoice-1]})");
                } else if((userChoice == 1 && botChoice == 3) || (userChoice == 2 && botChoice == 1) || (userChoice == 3 && botChoice == 2))
                {
                    Console.WriteLine($"\nVOCÊ GANHOU! Sua escolha foi {options[userChoice-1]} e a do seu adversário foi {options[botChoice-1]}");
                    userPoints += 1;
                } else
                {
                    Console.WriteLine($"\nVOCÊ PERDEU! Sua escolha foi {options[userChoice-1]} e a do seu adversário foi {options[botChoice-1]}");
                    botPoints += 1;
                }

                if(userPoints == 5)
                {
                    Console.WriteLine(new string('-', 30));
                    Console.WriteLine("FIM DE JOGO. VOCÊ FOI O VENCEDOR, PARABÉNS!");
                    Console.Write("\nPressione a tecla ENTER para voltar ao menu: ");
                    Console.WriteLine(new string('-', 30));
                    Console.ReadLine();
                    gameFinished=true;
                    Game.menu();

                } else if(botPoints == 5)
                {
                    Console.WriteLine(new string('-', 30));
                    Console.WriteLine("FIM DE JOGO. O ADVERSÁRIO FOI O VENCEDOR, BOA SORTE NA PRÓXIMA!");
                    Console.Write("\nPressione a tecla ENTER para voltar ao menu: ");
                    Console.ReadLine();
                    gameFinished = true;
                    Game.menu();
                }
            }
        }
    }
}