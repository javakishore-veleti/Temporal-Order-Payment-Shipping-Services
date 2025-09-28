THIS_SCRIPT_DIR=$(dirname $0)
echo "THIS_SCRIPT_DIR ${THIS_SCRIPT_DIR}"

echo "Starting Postgres DB Server for the Application"
# docker compose -f "${THIS_SCRIPT_DIR}"/postgres/docker-compose.yaml up -d
echo "DONE Starting Postgres DB Server for the Application"

sleep 2

echo "Starting temporal-server"
docker compose -f "${THIS_SCRIPT_DIR}"/temporal-server/docker-compose.yml up -d
echo "DONE Starting temporal-server"
docker ps -a