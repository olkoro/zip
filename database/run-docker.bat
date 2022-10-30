docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=QWERTYuiop1234" --name zipsqlserver -p 1433:1433 -d mcr.microsoft.com/mssql/server:2022-latest
pause
