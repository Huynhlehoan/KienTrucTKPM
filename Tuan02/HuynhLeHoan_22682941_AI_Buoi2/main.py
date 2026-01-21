import pandas as pd
from data_preprocessing import MiniPreprocessor

df = pd.DataFrame({
    'Diem': [8, 9, None, 7, 10],
    'MonHoc': ['Toan', 'Ly', 'Toan', 'Hoa', 'Ly']
})

tool = MiniPreprocessor(df)
df_clean = tool.handle_missing_values(['Diem'], strategy='mean')

print("Du lieu sau khi xu ly:")
print(df_clean)