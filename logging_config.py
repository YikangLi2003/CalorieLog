import logging
import os

LOGGING_FILE_NAME = f"{os.path.dirname(os.path.abspath(__file__))}/app_logs.log"

logger = logging.getLogger("my_logger")
logger.setLevel(logging.INFO)

console_handler = logging.StreamHandler()
console_handler.setLevel(logging.INFO)

file_handler = logging.FileHandler(LOGGING_FILE_NAME)
file_handler.setLevel(logging.INFO)

formatter = logging.Formatter("[%(levelname)s %(asctime)s] %(message)s", datefmt="%Y-%m-%d %H:%M:%S")
console_handler.setFormatter(formatter)
file_handler.setFormatter(formatter)

logger.addHandler(console_handler)
logger.addHandler(file_handler)
