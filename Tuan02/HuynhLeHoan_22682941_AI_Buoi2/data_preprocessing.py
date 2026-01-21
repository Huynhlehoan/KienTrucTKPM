import numpy as np
import pandas as pd

class MiniPreprocessor:
    def __init__(self, df: pd.DataFrame):
        """
        Khởi tạo với DataFrame.
        
        """
        self.df = df.copy()

    def handle_missing_values(self, columns, strategy='mean'):
        """
        Xử lý dữ liệu bị thiếu (NaN).
        Strategy: 'mean' (trung bình), 'median' (trung vị), 'mode' (yếu vị/xuất hiện nhiều nhất).
        """
        for col in columns:
            if col not in self.df.columns:
                continue
            
            if strategy == 'mean':
                fill_val = self.df[col].mean()
            elif strategy == 'median':
                fill_val = self.df[col].median()
            elif strategy == 'mode':
                fill_val = self.df[col].mode()[0]
            else:
                raise ValueError("Strategy phải là 'mean', 'median' hoặc 'mode'")
            
            self.df[col] = self.df[col].fillna(fill_val)
        return self.df

    def normalize(self, columns, method='minmax'):
        """
        Chuẩn hóa dữ liệu về dạng 0-1 (MinMax) hoặc Z-score (Standard).
        Math: X_new = (X - min) / (max - min)
        """
        for col in columns:
            if method == 'minmax':
                min_val = self.df[col].min()
                max_val = self.df[col].max()
                # Tránh chia cho 0 nếu min == max
                if max_val != min_val:
                    self.df[col] = (self.df[col] - min_val) / (max_val - min_val)
                else:
                    self.df[col] = 0.0
            elif method == 'zscore':
                mean_val = self.df[col].mean()
                std_val = self.df[col].std()
                if std_val != 0:
                    self.df[col] = (self.df[col] - mean_val) / std_val
                else:
                    self.df[col] = 0.0
        return self.df

    def encode_categorical(self, column, type='onehot'):
        """
        Mã hóa biến phân loại (Category).
        Type: 'onehot' (ra nhiều cột 0/1) hoặc 'label' (ra số 0, 1, 2...).
        """
        if type == 'label':
            # Lấy danh sách giá trị duy nhất và tạo dict map: {'Red': 0, 'Blue': 1...}
            unique_vals = self.df[column].unique()
            mapping = {val: idx for idx, val in enumerate(unique_vals)}
            self.df[column] = self.df[column].map(mapping)
            
        elif type == 'onehot':
            # Tự code One-Hot không dùng pd.get_dummies (để hiểu bản chất)
            unique_vals = self.df[column].unique()
            for val in unique_vals:
                # Tạo cột mới: ví dụ Color_Red
                new_col_name = f"{column}_{val}"
                # So sánh và ép kiểu về int (True->1, False->0)
                self.df[new_col_name] = (self.df[column] == val).astype(int)
            # Xóa cột gốc
            self.df = self.df.drop(columns=[column])
            
        return self.df

    def train_test_split(self, target_col, test_size=0.2, random_seed=42):
        """
        Chia tập Train/Test thủ công bằng Numpy Indexing.
        """
        # Đặt seed để kết quả giống nhau mỗi lần chạy
        np.random.seed(random_seed)
        
        # Trộn ngẫu nhiên index
        shuffled_indices = np.random.permutation(len(self.df))
        
        # Tính điểm cắt
        test_set_size = int(len(self.df) * test_size)
        
        # Chia index
        test_indices = shuffled_indices[:test_set_size]
        train_indices = shuffled_indices[test_set_size:]
        
        # Cắt DataFrame theo index
        train_df = self.df.iloc[train_indices]
        test_df = self.df.iloc[test_indices]
        
        # Tách X (Features) và y (Target)
        X_train = train_df.drop(columns=[target_col])
        y_train = train_df[target_col]
        X_test = test_df.drop(columns=[target_col])
        y_test = test_df[target_col]
        
        return X_train, X_test, y_train, y_test

# --- TEST CODE ---
if __name__ == "__main__":
    # Tạo data giả
    data = {
        'Age': [25, np.nan, 30, 22, 35, np.nan],
        'Salary': [50000, 54000, 60000, 48000, 58000, 52000],
        'City': ['Hanoi', 'HCM', 'Hanoi', 'Danang', 'HCM', 'Danang'],
        'Purchased': [0, 1, 0, 0, 1, 1]
    }
    df = pd.DataFrame(data)
    print("Original Data:\n", df)

    # Khởi tạo processor
    processor = MiniPreprocessor(df)

    # 1. Xử lý thiếu dữ liệu
    processor.handle_missing_values(['Age'], strategy='mean')
    print("\n1. After Fill NaN:\n", processor.df)

    # 2. Chuẩn hóa Min-Max
    processor.normalize(['Salary', 'Age'])
    print("\n2. After Normalize:\n", processor.df)

    # 3. Encode Categorical (One-Hot cho City)
    processor.encode_categorical('City', type='onehot')
    print("\n3. After One-Hot Encoding:\n", processor.df)

    # 4. Split Train/Test
    X_train, X_test, y_train, y_test = processor.train_test_split(target_col='Purchased', test_size=0.2)
    print(f"\n4. Train Size: {len(X_train)}, Test Size: {len(X_test)}")