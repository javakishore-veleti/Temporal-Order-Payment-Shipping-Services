THIS_SCRIPT_DIR=$(dirname $0)
echo "THIS_SCRIPT_DIR ${THIS_SCRIPT_DIR}"
docker compose -f "${THIS_SCRIPT_DIR}"/postgres/docker-compose.yaml up -d

docker ps -a